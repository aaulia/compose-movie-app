package aaulia.compose.movie.data.local.model.relation

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "movie_cast_relation", primaryKeys = ["movieId", "castId"])
data class MovieCastRelation(
    val movieId: Int,
    @ColumnInfo(index = true)
    val castId: Int
)