package aaulia.compose.movie.features.list

import aaulia.compose.movie.ui.theme.MovieAppTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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

enum class MovieType {
    PLAYING,
    POPULAR,
    NEARING
}


@Composable
fun List(
    movieType: MovieType,
    viewModel: ListViewModel = viewModel(
        factory = viewModelFactory {
            initializer {
                ListViewModel(movieType)
            }
        })
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(viewModel.movies) { index ->
            ListItem(index)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ListPreview() {
    MovieAppTheme {
        List(MovieType.PLAYING)
    }
}


@Composable
fun ListItem(index: Int) {
    Surface(
        modifier = Modifier
            .safeContentPadding()
            .height(192.dp),
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