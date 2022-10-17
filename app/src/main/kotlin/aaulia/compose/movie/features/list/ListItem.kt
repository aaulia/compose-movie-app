package aaulia.compose.movie.features.list

import aaulia.compose.movie.BuildConfig
import aaulia.compose.movie.R
import aaulia.compose.movie.features.list.model.Movie
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun ListItem(movie: Movie?, onClick: (Int) -> Unit = { }) {
    if (movie != null) {
        Box(
            modifier = Modifier
                .aspectRatio(0.6666667f)
                .clip(RoundedCornerShape(8.dp))
                .clickable { onClick(movie.id) }
        ) {
            if (movie.poster.isNotEmpty()) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(movie.poster)
                        .crossfade(true)
                        .build(),
                    contentScale = ContentScale.FillWidth,
                    contentDescription = movie.title,
                    placeholder = painterResource(id = R.drawable.placeholder_loading),
                    error = painterResource(id = R.drawable.placeholder_failure),
                    modifier = Modifier.fillMaxWidth(),
                )
            } else {
                Image(
                    painter = painterResource(id = R.drawable.placeholder_missing),
                    contentDescription = movie.title,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier.fillMaxWidth(),
                )
            }

            if (movie.title.isNotEmpty()) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .background(color = MaterialTheme.colors.primary)
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .height(48.dp),
                ) {
                    Text(
                        text = movie.title,
                        color = MaterialTheme.colors.onPrimary,
                        style = MaterialTheme.typography.caption,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = 16.dp),
                    )
                }
            }
        }
    } else {
        Image(
            painter = painterResource(id = R.drawable.placeholder_default),
            contentDescription = "Poster Placeholder",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(0.6666667f)
                .clip(RoundedCornerShape(8.dp)),
        )
    }
}

@Preview
@Composable
fun PlaceholderPreview() {
    ListItem(null)
}

@Preview
@Composable
fun MissingPreview() {
    ListItem(Movie(0, "", ""))
}

@Preview
@Composable
fun ListItemPreview() {
    val mock = Movie(
        id = 0,
        title = "Thor: Love and Thunder",
        poster = "${BuildConfig.TMDB_IMG_URL}/${BuildConfig.TMDB_POSTER_SIZE}/pIkRyD18kl4FhoCNQuWxWu5cBLM.jpg"
    )
    ListItem(mock)
}
