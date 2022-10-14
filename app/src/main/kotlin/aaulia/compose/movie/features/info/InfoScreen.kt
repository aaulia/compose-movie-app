package aaulia.compose.movie.features.info

import aaulia.compose.movie.features.info.model.Movie
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory

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
    val movie by viewModel.movie.collectAsState(initial = Movie("", ""))

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = movie.title,
                            overflow = TextOverflow.Ellipsis
                        )

                        if (movie.tagline.isNotEmpty()) {
                            Text(
                                text = movie.tagline,
                                overflow = TextOverflow.Ellipsis,
                                fontSize = 12.sp,
                                fontStyle = FontStyle.Italic,
                                fontWeight = FontWeight.Normal
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
    ) {
        Surface(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {

        }
    }
}
