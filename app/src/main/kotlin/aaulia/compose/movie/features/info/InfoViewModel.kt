package aaulia.compose.movie.features.info

import aaulia.compose.movie.data.repository.MovieRepository
import aaulia.compose.movie.di.Injector
import aaulia.compose.movie.features.info.model.MovieCommon
import aaulia.compose.movie.features.info.model.MovieExtras
import aaulia.compose.movie.features.info.model.toMovieCommon
import aaulia.compose.movie.features.info.model.toMovieExtras
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import aaulia.compose.movie.data.local.model.MovieCommon as LocalMovieCommon
import aaulia.compose.movie.data.local.model.MovieDetail as LocalMovieDetail

class InfoViewModel(
    movieId: Int,
    movieRepo: MovieRepository = Injector.repository
) : ViewModel() {

    init {
        viewModelScope.launch {
            movieRepo.getDetails(movieId)
        }
    }

    val common = movieRepo.queryMovieCommon(movieId).map(LocalMovieCommon::toMovieCommon)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), MovieCommon())

    val extras = movieRepo.queryMovieDetail(movieId).map(LocalMovieDetail::toMovieExtras)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), MovieExtras())
}