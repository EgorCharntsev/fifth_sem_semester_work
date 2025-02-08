package ru.kpfu.itis.impl.presentation.ui

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.flow.collectLatest
import ru.kpfu.itis.common.presentation.model.AlbumUiModel
import ru.kpfu.itis.common.presentation.model.ArtistUiModel
import ru.kpfu.itis.common.presentation.model.SearchResultUiModel
import ru.kpfu.itis.common.presentation.model.SongUiModel
import ru.kpfu.itis.common.presentation.model.UiSearchable

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchScreen(navController: NavController, searchViewModel: SearchViewModel = hiltViewModel()) {
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val searchResults by searchViewModel.searchResultsState.collectAsState()
    var query by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        searchViewModel.errorsChannel.collectLatest { error ->
            Toast.makeText(context, error.message, Toast.LENGTH_LONG).show()
        }
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        TextField(
            value = query,
            onValueChange = {
                query = it
                searchViewModel.search(it)
            },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Search...") },
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(
                onSearch = {
                    searchViewModel.search(query)
                    keyboardController?.hide()
                    focusManager.clearFocus()
                }
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
//            LazyColumn(modifier = Modifier.fillMaxSize()) {
//                for (searchRes in searchResults!!) run {
//                    searchRes.
//
//
//                }
//                items(searchResults.,  ?: emptyList()) { item ->
//                    SearchResultItem(item, navController)
//                }
//            }
        }
    }
}

//@Composable
//fun SearchResultItem(item: SearchResultUiModel, navController: NavController) {
//    Column(modifier = Modifier
//        .fillMaxWidth()
//        .clickable {
//            when (item.sections) {
//                is SongUiModel -> {
//                    navController.navigate("song/${item.id}")
//                }
//                is AlbumUiModel -> {
//                    navController.navigate("album/${item.id}")
//                }
//                is ArtistUiModel -> {
//                    navController.navigate("artist/${item.id}")
//                }
//            }
//        }
//        .padding(8.dp)
//    ) {
//        Text(text = item.sections, style = MaterialTheme.typography.h6)
//        Text(text = item.subtitle, style = MaterialTheme.typography.body2, color = MaterialTheme.colors.onSurface.copy(alpha = 0.7f))
//        Divider()
//    }
//}