package aaulia.compose.movie.features.list

import aaulia.compose.movie.data.repository.TMDBRepository
import aaulia.compose.movie.ui.theme.MovieAppTheme
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(
            count = movies.itemCount,
        ) { index ->
            movies[index]?.let { movie -> ListItem(movie.id, onClick) }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ListPreview() {
    MovieAppTheme {
        ListScreen(MovieType.PLAYING)
    }
}


@Composable
fun ListItem(
    index: Int,
    onClick: (Int) -> Unit = { }
) {
    Surface(
        modifier = Modifier
            .safeContentPadding()
            .height(192.dp)
            .clickable {
                onClick(index)
            },
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colors.secondary
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "$index")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ListItemPreview() {
    MovieAppTheme {
        ListItem(8)
    }
}