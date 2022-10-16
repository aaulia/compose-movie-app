package aaulia.compose.movie.data.local.dao

import aaulia.compose.movie.data.local.model.Genre
import aaulia.compose.movie.data.local.model.Movie
import aaulia.compose.movie.data.local.model.MovieDetail
import aaulia.compose.movie.data.local.model.relation.MovieGenreRelation
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(vararg movies: Movie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenres(vararg genres: Genre)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieGenreRelations(vararg refs: MovieGenreRelation)

    @Transaction
    @Query("SELECT * FROM movies WHERE movieId = :id LIMIT 1")
    fun selectMovieDetail(id: Int): Flow<MovieDetail>

}