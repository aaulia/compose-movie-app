package aaulia.compose.movie.data.local.model

import aaulia.compose.movie.data.remote.model.MovieSimple
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class Movie(
    @PrimaryKey
    val id: Int,
    val imdbId: String,

    val title: String,
    val tagline: String,
    val overview: String,

    val popularity: Float,
    val voteAverage: Float,
    val voteCount: Int,

    val posterPath: String,
    val backdropPath: String,

    val homepage: String
)

fun MovieSimple.toMovie() =
    Movie(
        id = id,
        imdbId = "",
        title = title,
        tagline = "",
        overview = overview,
        popularity = popularity,
        voteAverage = voteAverage,
        voteCount = voteCount,
        posterPath = posterPath,
        backdropPath = backdropPath,
        homepage = ""
    )