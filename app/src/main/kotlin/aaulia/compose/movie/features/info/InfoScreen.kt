package aaulia.compose.movie.features.info

import aaulia.compose.movie.R
import aaulia.compose.movie.features.info.model.Movie
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun InfoScreen(
    movieId: Int,
    onNavigateBack: () -> Unit = { },
    viewModel: InfoViewModel = viewModel(
        factory = viewModelFactory {
            initializer {
                InfoViewModel(movieId)
            }
        })
) {
    val movie by viewModel.movie.collectAsState(initial = Movie.EMPTY)

    Scaffold(
        topBar = {
            InfoTopBar(
                movie = movie,
                onNavigateBack = onNavigateBack
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(movie.backdropPath)
                    .crossfade(true)
                    .build(),
                contentDescription = "",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .alpha(0.5f)
                    .aspectRatio(16.0f / 9.0f)
                    .fillMaxWidth()
            )

            Column(
                modifier = Modifier
                    .offset(y = (-128).dp)
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(movie.posterPath)
                        .crossfade(true)
                        .build(),
                    contentDescription = "",
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .width(128.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .height(16.dp))

                Text(
                    text = stringResource(id = R.string.overview),
                    style = MaterialTheme.typography.h6
                )

                Text(
                    text = movie.overview,
                    style = MaterialTheme.typography.caption
                )
            }
        }
    }
}


@Composable
fun InfoTopBar(
    movie: Movie,
    onNavigateBack: () -> Unit = {}
) {
    TopAppBar(
        title = {
            Column {
                Text(
                    text = movie.title,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )

                if (movie.tagline.isNotEmpty()) {
                    Text(
                        text = movie.tagline,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.caption,
                        maxLines = 1
                    )
                }
            }
        },
        navigationIcon = {
            IconButton(onClick = onNavigateBack) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = ""
                )
            }
        }
    )
}
