package aaulia.compose.movie.data.repository

import aaulia.compose.movie.data.model.MoviePage

interface MovieRepository {
    suspend fun getPlaying(page: Int): MoviePage
    suspend fun getPopular(page: Int): MoviePage
    suspend fun getNearing(page: Int): MoviePage
}