package ru.kpfu.itis.impl.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.kpfu.itis.impl.presentation.model.ChartsSongItemModel
import ru.kpfu.itis.impl.usecase.GetChartsUseCaseImpl
import ru.kpfu.itis.common.exception.handler.ExceptionHandlerDelegate
import ru.kpfu.itis.common.exception.handler.runCatching
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getChartsUseCase: GetChartsUseCaseImpl,
    private val exceptionHandlerDelegate: ExceptionHandlerDelegate,
) : ViewModel() {

    private val _chartsState = MutableStateFlow<List<ChartsSongItemModel>?>(null)
    val chartsState: StateFlow<List<ChartsSongItemModel>?> = _chartsState.asStateFlow()

    private val _errorState = MutableSharedFlow<String>()
    val errorState: SharedFlow<String> = _errorState

    init {
        updateCharts()
    }

    fun updateCharts() {
        viewModelScope.launch {
            runCatching(exceptionHandlerDelegate) {
                getChartsUseCase().mapIndexed { index, song ->
                    ChartsSongItemModel(index + 1, song)
                }
            }
                .onSuccess { _chartsState.value = it }
                .onFailure { error ->
                    _errorState.emit(error.message ?: "Unknown error")
                    _chartsState.value = emptyList()
                }
        }
    }
}
