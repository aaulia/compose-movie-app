package aaulia.compose.movie.features.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.paging.compose.collectAsLazyPagingItems

enum class MovieType {
    PLAYING,
    POPULAR,
    NEARING
}


@Composable
fun ListScreen(
    movieType: MovieType,
    onClick: (Int) -> Unit = { },
    viewModel: ListViewModel = viewModel(
        factory = viewModelFactory {
            initializer {
                ListViewModel(movieType)
            }
        })
) {
    val movies = viewModel.movieFlow.collectAsLazyPagingItems()

    LazyVerticalGrid(
        columns = GridCells.Adaptive(96.dp),
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(
            count = movies.itemCount,
            key = { it }
        ) { index ->
            movies[index]?.let { movie -> ListItem(movie, onClick) }
        }
    }
}
