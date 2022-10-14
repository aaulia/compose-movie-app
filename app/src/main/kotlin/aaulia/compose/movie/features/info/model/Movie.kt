package aaulia.compose.movie.features.info.model

import aaulia.compose.movie.data.local.model.Movie as MovieEntity

data class Movie(
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
) {
    companion object {
        val EMPTY = Movie(
            imdbId = "",
            title = "",
            tagline = "",
            overview = "",
            popularity = 0.0f,
            voteAverage = 0.0f,
            voteCount = 0,
            posterPath = "",
            backdropPath = "",
            homepage = ""
        )
    }
}

fun MovieEntity.toMovie() =
    Movie(
        imdbId,
        title,
        tagline,
        overview,
        popularity,
        voteAverage,
        voteCount,
        posterPath,
        backdropPath,
        homepage
    )
