package aaulia.compose.movie.ui.component

import aaulia.compose.movie.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun MoviePoster(
    image: String,
    modifier: Modifier = Modifier,
    title: String = "",
    onClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .aspectRatio(0.6666667f)
            .clickable { onClick() }
    ) {
        if (image.isNotEmpty()) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(image)
                    .crossfade(true)
                    .build(),
                contentScale = ContentScale.Crop,
                contentDescription = title,
                placeholder = painterResource(id = R.drawable.placeholder_loading),
                error = painterResource(id = R.drawable.placeholder_failure),
                modifier = Modifier.fillMaxWidth(),
            )
        } else {
            Image(
                painter = painterResource(id = R.drawable.placeholder_missing),
                contentDescription = title,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth(),
            )
        }

        if (title.isNotEmpty()) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .background(color = MaterialTheme.colors.primary.copy(alpha = 0.9f))
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .height(48.dp),
            ) {
                Text(
                    text = title,
                    color = MaterialTheme.colors.onPrimary,
                    style = MaterialTheme.typography.caption,
                    textAlign = TextAlign.Center,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2,
                    modifier = Modifier.padding(horizontal = 16.dp),
                )
            }
        }
    }
}
