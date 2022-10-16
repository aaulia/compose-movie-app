package aaulia.compose.movie.data.local.model.relation

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "movie_genre_relation", primaryKeys = ["movieId", "genreId"])
data class MovieGenreRelation(
    val movieId: Int,
    @ColumnInfo(index = true)
    val genreId: Int
)