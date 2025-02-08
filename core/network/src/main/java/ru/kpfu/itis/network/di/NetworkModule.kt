package ru.kpfu.itis.network.di

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Request
import org.jsoup.Jsoup
import org.jsoup.safety.Safelist
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.gildor.coroutines.okhttp.await
import ru.kpfu.itis.network.api.GeniusLyricsApi
import ru.kpfu.itis.network.api.PublicGeniusApi
import ru.kpfu.itis.network.model.SongLyricsDataModel
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .build()
    }

    @Provides
    @Singleton
    fun provideGeniusApi(http: OkHttpClient): PublicGeniusApi {
        return Retrofit.Builder()
            .baseUrl("https://genius.com/api/")
            .client(http)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PublicGeniusApi::class.java)
    }

    @Provides
    @Singleton
    fun provideLyricsClient(http: OkHttpClient): GeniusLyricsApi {
        return object : GeniusLyricsApi {
            override suspend fun getLyrics(url: String): SongLyricsDataModel? {
                val req = Request.Builder()
                    .get()
                    .url(url)
                    .build()
                val body = http.newCall(req).await().body()?.string() ?: return null
                val doc = Jsoup.parse(body)
                val lyricsElems = doc.select("[class^=Lyrics__Container]")
                lyricsElems.select("br").append("\\n")
                val lyricsHtml = lyricsElems.html()
                val lyricsText = Jsoup.clean(lyricsHtml, Safelist.none())
                    .replace("\\n", System.lineSeparator())
                    .lines()
                    .joinToString(System.lineSeparator(), transform = String::trim)
                return SongLyricsDataModel(lyricsText)
            }
        }
    }

    @Provides
    @Singleton
    fun provideGson(): Gson = Gson()
}