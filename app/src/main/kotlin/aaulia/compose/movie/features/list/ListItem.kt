package aaulia.compose.movie.features.list

import aaulia.compose.movie.BuildConfig
import aaulia.compose.movie.R
import aaulia.compose.movie.features.list.model.Movie
import aaulia.compose.movie.ui.component.MoviePoster
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ListItem(movie: Movie?, onClick: (Int) -> Unit = { }) {
    if (movie != null) {
        MoviePoster(
            modifier = Modifier.clip(RoundedCornerShape(8.dp)),
            image = movie.poster,
            title = movie.title,
            onClick = { onClick(movie.id) },
        )
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
