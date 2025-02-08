package ru.kpfu.itis.fifthsemwork.ui

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
import ru.kpfu.itis.common.navigation.Route
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
) : ViewModel() {

    fun getStartDestination() : Route = runBlocking {
        Route.BottomMenu.Home
    }
}
