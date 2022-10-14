package aaulia.compose.movie.data.repository

import aaulia.compose.movie.data.local.model.Movie
import aaulia.compose.movie.data.remote.model.MoviePage
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getPlaying(page: Int): MoviePage
    suspend fun getPopular(page: Int): MoviePage
    suspend fun getNearing(page: Int): MoviePage
    suspend fun fetchMovie(id: Int)

    fun queryMovie(id: Int): Flow<Movie>
}