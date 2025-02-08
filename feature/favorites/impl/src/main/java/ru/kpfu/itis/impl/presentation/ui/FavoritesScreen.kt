package ru.kpfu.itis.impl.presentation.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ru.kpfu.itis.common.presentation.model.FavoriteSongUiModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(
    navController: NavController,
    favoritesViewModel : FavoritesViewModel = hiltViewModel()
) {
    val favoritesState = favoritesViewModel.favoritesState.collectAsState(initial = null)

    // Show progress bar or list based on the state
    when (val favorites = favoritesState.value) {
        null -> {
            // Loading state
            CircularProgressIndicator(
                modifier = Modifier.fillMaxSize()
            )
        }
        else -> {
            // Favorites are loaded
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(favorites) { song ->
                    FavoriteSongItem(
                        song = song,
                        onClick = {
                            goToSong(
                                song,
                                navController,
                            )
                        })
                }
            }
        }
    }

    // Observe ViewModel and fetch favorites
    LaunchedEffect(Unit) {
        favoritesViewModel.loadFavorites()
    }
}

@Composable
fun FavoriteSongItem(
    song: FavoriteSongUiModel,
    onClick: (FavoriteSongUiModel) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick(song) },
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BasicText(text = song.title)
            IconButton(onClick = { /* Handle favorite button click */ }) {
                Icon(imageVector = Icons.Default.Favorite, contentDescription = "Favorite")
            }
        }
    }
}

fun goToSong(song: FavoriteSongUiModel, navController: NavController) {
    //val bundle = bundleOf(SongFragment.SONG_ID_KEY to song.id)
    navController.navigate("song/${song.id}")
}

// Preview composable for testing UI
@Preview
@Composable
fun MyMusicScreenPreview() {
    FavoritesScreen(navController = rememberNavController())
}
