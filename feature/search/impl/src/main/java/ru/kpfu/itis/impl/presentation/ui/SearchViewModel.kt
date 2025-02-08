package ru.kpfu.itis.impl.presentation.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import ru.kpfu.itis.common.exception.handler.ExceptionHandlerDelegate
import ru.kpfu.itis.common.exception.handler.runCatching
import ru.kpfu.itis.common.presentation.model.SearchResultItemModel
import ru.kpfu.itis.impl.usecase.SearchUseCase
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase,
    private val exceptionHandlerDelegate: ExceptionHandlerDelegate
) : ViewModel() {

    private val _searchResultsState = MutableStateFlow<List<SearchResultItemModel>?>(emptyList())
    val searchResultsState = _searchResultsState.asStateFlow()

    private val _errorsChannel = Channel<Throwable>()
    val errorsChannel = _errorsChannel.receiveAsFlow()

    private var searchJob: Job? = null

    fun search(query: String) {
        searchJob?.cancel()

        if (query.isBlank()) {
            _searchResultsState.value = emptyList()
            return
        }

        searchJob = viewModelScope.launch {
            delay(timeMillis = 500) // debounce
            _searchResultsState.value = null
            runCatching(exceptionHandlerDelegate) {
                searchUseCase(query)
            }.onSuccess {
                _searchResultsState.value = it.sections.flatMap { section ->
                    if (section.items.isNotEmpty()) {
                        mutableListOf<SearchResultItemModel>(section).apply {
                            addAll(section.items)
                        }
                    } else {
                        emptyList()
                    }
                }
            }.onFailure { error ->
                if (error !is CancellationException) {
                    _errorsChannel.send(error)
                    _searchResultsState.value = emptyList()
                }
            }
        }
    }
}