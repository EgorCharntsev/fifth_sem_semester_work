package ru.kpfu.itis.impl.presentation.ui

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil3.Bitmap
import coil3.ImageLoader
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.SuccessResult
import coil3.request.crossfade
import com.google.android.material.progressindicator.CircularProgressIndicator
import ru.kpfu.itis.impl.presentation.model.ChartsSongItemModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavController,
    mainViewModel: MainViewModel = hiltViewModel()
) {
    val chartsState by mainViewModel.chartsState.collectAsState()
    val context = LocalContext.current

    // Подписка на ошибки
    LaunchedEffect(Unit) {
        mainViewModel.errorState.collect { error ->
            Toast.makeText(context, error, Toast.LENGTH_LONG).show()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Charts") })
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when {
//                chartsState == null -> CircularProgressIndicator(
//                    modifier = Modifier.align(Alignment.Center)
//                )
                chartsState != null -> LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    item { ChartsHeaderItem() }
                    items(chartsState!!) { item ->
                        ChartItem(item, onClick = { goToSong(navController, item) })
                    }
                }
            }
        }
    }
}

// Заголовок списка
@Composable
fun ChartsHeaderItem() {
    Text(
        text = "Top Charts",
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(16.dp)
    )
}

// Элемент списка
@Composable
fun ChartItem(item: ChartsSongItemModel, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = item.song.title,
            fontSize = 18.sp,
            modifier = Modifier.weight(1f)
        )
//        Icon(
//            imageVector = AsyncImage(
//                model = ((ImageLoader(LocalContext.current)
//                    .execute(
//                        ImageRequest.Builder(LocalContext.current)
//                        .data(item.song.imageUrl)
//                        .crossfade(true)
//                        .build()) as? SuccessResult)?.drawable) as? Bitmap)?.asImageBitmap()
//                    contentDescription = "Загруженное изображение")
//            contentDescription = null
//        )
    }
}

// Навигация к SongFragment
fun goToSong(navController: NavController, item: ChartsSongItemModel) {
    navController.navigate("song/${item.song.id}")
}
