package aaulia.compose.movie.data.local

import aaulia.compose.movie.data.local.dao.MovieDao
import aaulia.compose.movie.data.local.model.Cast
import aaulia.compose.movie.data.local.model.Genre
import aaulia.compose.movie.data.local.model.Movie
import aaulia.compose.movie.data.local.model.relation.MovieCastRelation
import aaulia.compose.movie.data.local.model.relation.MovieGenreRelation
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        Movie::class,

        Genre::class,
        MovieGenreRelation::class,

        Cast::class,
        MovieCastRelation::class,
    ],
    exportSchema = false,
    version = 1
)
abstract class MovieDb : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}