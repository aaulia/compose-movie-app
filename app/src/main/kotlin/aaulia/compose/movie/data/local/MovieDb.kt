package aaulia.compose.movie.data.local

import aaulia.compose.movie.data.local.dao.MovieDao
import aaulia.compose.movie.data.local.model.Movie
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Movie::class], version = 1)
abstract class MovieDb : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}