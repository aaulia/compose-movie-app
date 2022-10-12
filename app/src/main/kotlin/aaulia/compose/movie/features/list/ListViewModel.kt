package aaulia.compose.movie.features.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ListViewModel(movieType: MovieType) : ViewModel() {
    var movies by mutableStateOf((1..100).toList())
        private set
}