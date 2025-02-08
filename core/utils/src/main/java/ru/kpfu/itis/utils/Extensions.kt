package ru.kpfu.itis.genius.util

import android.content.Context
import android.util.TypedValue
import kotlin.math.floor
import kotlin.math.log10
import kotlin.math.pow

fun Context.toPx(dp: Int): Int = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP,
    dp.toFloat(),
    resources.displayMetrics
).toInt()

//@Suppress("RecursivePropertyAccessor")
//val Context.appComponent: AppComponent
//    get() = when (this) {
//        is App -> appComponent
//        else -> this.applicationContext.appComponent
//    }

val Int.formatted: String
    get() {
        val suffixes = arrayOf("", "K", "M", "B")
        val zeros = floor(log10(toDouble())).toInt()
        val suffixIndex = zeros / 3

        return if (suffixIndex >= 1) {
            String.format("%.1f%s", this / 10.0.pow(suffixIndex * 3.0), suffixes[suffixIndex])
        } else {
            toString()
        }
    }
