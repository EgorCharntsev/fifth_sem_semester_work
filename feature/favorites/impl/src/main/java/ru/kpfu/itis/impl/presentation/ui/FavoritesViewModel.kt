package ru.kpfu.itis.impl.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.kpfu.itis.common.presentation.model.FavoriteSongUiModel
import ru.kpfu.itis.impl.usecase.DeleteFavoriteSongUseCase
import ru.kpfu.itis.impl.usecase.GetRecentFavoriteSongsUseCase
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getRecentFavoriteSongsUseCase: GetRecentFavoriteSongsUseCase,
    private val deleteFavoriteSongUseCase: DeleteFavoriteSongUseCase
) : ViewModel() {

    private val _favoritesState = MutableStateFlow<List<FavoriteSongUiModel>>(emptyList())
    val favoritesState: StateFlow<List<FavoriteSongUiModel>> = _favoritesState.asStateFlow()

    init {
        loadFavorites()
    }

    fun loadFavorites() {
        viewModelScope.launch {
            _favoritesState.value = getRecentFavoriteSongsUseCase() ?: emptyList()
        }
    }

    fun deleteFavorite(song: FavoriteSongUiModel) {
        viewModelScope.launch {
            deleteFavoriteSongUseCase(song.id)
            _favoritesState.value = _favoritesState.value.filter { it.id != song.id }
        }
    }
}