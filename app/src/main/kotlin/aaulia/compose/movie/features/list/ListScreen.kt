package aaulia.compose.movie.features.list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
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


@OptIn(ExperimentalFoundationApi::class)
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

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(
            count = movies.itemCount
        ) { index ->
            movies[index]?.let { movie -> ListItem(movie, onClick) }
        }
    }
}
