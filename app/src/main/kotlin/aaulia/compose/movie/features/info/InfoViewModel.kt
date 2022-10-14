package aaulia.compose.movie.features.info

import aaulia.compose.movie.data.local.model.Movie
import aaulia.compose.movie.data.repository.MovieRepository
import aaulia.compose.movie.di.Injector
import aaulia.compose.movie.features.info.model.toMovie
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.map

class InfoViewModel(
    movieId: Int,
    movieRepo: MovieRepository = Injector.repository
) : ViewModel() {
    val movie = movieRepo
        .fetchMovie(movieId)
        .map(Movie::toMovie)
}