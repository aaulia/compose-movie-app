package aaulia.compose.movie.features.info

import aaulia.compose.movie.R
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import coil.compose.AsyncImage
import coil.request.ImageRequest

@OptIn(ExperimentalLifecycleComposeApi::class)
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
    val common by viewModel.common.collectAsStateWithLifecycle(LocalLifecycleOwner.current)
    val extras by viewModel.extras.collectAsStateWithLifecycle(LocalLifecycleOwner.current)

    Scaffold(
        topBar = {
            InfoTopBar(
                title = common.title,
                subTitle = extras.tagline,
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
                    .data(common.backdrop)
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
                        .data(common.poster)
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
                    text = common.overview,
                    style = MaterialTheme.typography.caption
                )
            }
        }
    }
}


@Composable
fun InfoTopBar(
    title: String,
    subTitle: String,
    onNavigateBack: () -> Unit = {}
) {
    TopAppBar(
        title = {
            Column {
                Text(
                    text = title,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )

                if (subTitle.isNotEmpty()) {
                    Text(
                        text = subTitle,
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
