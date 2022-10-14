package aaulia.compose.movie.data.local.dao

import aaulia.compose.movie.data.local.model.Movie
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovies(vararg movies: Movie)

    @Update
    suspend fun updateMovie(movie: Movie)

    @Query("SELECT * FROM movies WHERE id = :id LIMIT 1")
    fun selectMovie(id: Int): Flow<Movie>

}