package aaulia.compose.movie.data.repository

import aaulia.compose.movie.data.local.dao.MovieDao
import aaulia.compose.movie.data.local.model.MovieDetail
import aaulia.compose.movie.data.local.model.relation.MovieGenreRelation
import aaulia.compose.movie.data.local.model.toGenre
import aaulia.compose.movie.data.local.model.toMovie
import aaulia.compose.movie.data.remote.TMDBService
import aaulia.compose.movie.data.remote.model.MovieGenre
import aaulia.compose.movie.data.remote.model.MoviePage
import aaulia.compose.movie.data.remote.model.MovieSimple
import kotlinx.coroutines.flow.Flow

class TMDBRepository(
    private val service: TMDBService,
    private val dao: MovieDao
) : MovieRepository {
    override suspend fun getPlaying(page: Int) = service.getPlaying(page).apply { store(this) }
    override suspend fun getPopular(page: Int) = service.getPopular(page).apply { store(this) }
    override suspend fun getNearing(page: Int) = service.getNearing(page).apply { store(this) }

    override suspend fun fetchMovie(id: Int) {
        val detail = service.getDetails(id)
        val genres = detail.genres.map(MovieGenre::toGenre).toTypedArray()
        val relMap = detail.genres
            .map { (genreId, _) -> MovieGenreRelation(detail.id, genreId) }
            .toTypedArray()

        dao.insertMovies(detail.toMovie())
        dao.insertGenres(*genres)
        dao.insertMovieGenreRelations(*relMap)
    }

    override fun queryMovieDetail(id: Int): Flow<MovieDetail> = dao.selectMovieDetail(id)

    private suspend fun store(moviePage: MoviePage) {
        val movieData = moviePage.results.map(MovieSimple::toMovie).toTypedArray()
        dao.insertMovies(*movieData)
    }
}