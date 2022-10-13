package aaulia.compose.movie.features.info

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun InfoScreen(onNavigateBack: () -> Unit = { }) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Movie Info") },
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