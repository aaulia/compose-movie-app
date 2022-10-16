package aaulia.compose.movie.data.local.model

import aaulia.compose.movie.data.local.model.relation.MovieGenreRelation
import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class MovieDetail(
    @Embedded
    val movie: Movie,

    @Relation(
        parentColumn = "movieId",
        entityColumn = "genreId",
        associateBy = Junction(MovieGenreRelation::class)
    )
    val genres: List<Genre>
)