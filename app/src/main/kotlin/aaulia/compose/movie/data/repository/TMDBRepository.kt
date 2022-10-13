package aaulia.compose.movie.data.repository

import aaulia.compose.movie.data.remote.TMDBService

class TMDBRepository(
    private val service: TMDBService
) : MovieRepository {
    override suspend fun getPlaying(page: Int) = service.getPlaying(page)
    override suspend fun getPopular(page: Int) = service.getPopular(page)
    override suspend fun getNearing(page: Int) = service.getNearing(page)
}