package aaulia.compose.movie.features.list.model

import aaulia.compose.movie.data.remote.model.MovieSimple
import aaulia.compose.movie.data.remote.model.fullPosterPath

data class Movie(
    val id: Int,
    val title: String,
    val poster: String
)

fun MovieSimple.toMovie() = Movie(id, title, fullPosterPath)