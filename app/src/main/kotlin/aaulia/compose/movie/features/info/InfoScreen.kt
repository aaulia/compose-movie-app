package aaulia.compose.movie.features.info

import aaulia.compose.movie.features.info.model.Movie
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
            modifier = Modifier.padding(paddingValues)
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
                        .padding(8.dp)
                        .width(128.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .align(Alignment.CenterHorizontally)
                )

                Text(
                    text = movie.overview,
                    modifier = Modifier.padding(horizontal = 16.dp),
                    textAlign = TextAlign.Center
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
                        fontSize = 12.sp,
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.Normal,
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
