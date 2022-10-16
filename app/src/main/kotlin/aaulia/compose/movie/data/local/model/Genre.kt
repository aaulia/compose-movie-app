package aaulia.compose.movie.data.local.model

import aaulia.compose.movie.data.remote.model.MovieGenre
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "genres")
data class Genre(
    @PrimaryKey
    val genreId: Int,
    val name: String
)

fun MovieGenre.toGenre() = Genre(id, name)

