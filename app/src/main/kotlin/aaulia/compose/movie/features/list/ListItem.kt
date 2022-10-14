package aaulia.compose.movie.features.list

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun ListItem(
    movie: Movie,
    onClick: (Int) -> Unit = { }
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .aspectRatio(2.0f / 3.0f)
            .clickable { onClick(movie.id) }
    ) {

        if (movie.poster.isNotEmpty()) {
            AsyncImage(
                model =
                ImageRequest.Builder(LocalContext.current)
                    .data(movie.poster)
                    .crossfade(true)
                    .build(),
                contentScale = ContentScale.FillWidth,
                contentDescription = movie.title,
                placeholder = painterResource(id = R.drawable.placeholder_loading),
                error = painterResource(id = R.drawable.placeholder_failure),
                modifier = Modifier.fillMaxWidth()
            )
        } else {
            Image(
                painter = painterResource(id = R.drawable.placeholder_default),
                contentDescription = movie.title,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .background(color = MaterialTheme.colors.primary)
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(48.dp)
        ) {
            Text(
                text = movie.title,
                color = MaterialTheme.colors.onPrimary,
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(
                    horizontal = 16.dp,
                    vertical = 4.dp
                )
            )
        }
    }
}
