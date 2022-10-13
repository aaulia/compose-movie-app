package aaulia.compose.movie.data.model

import kotlinx.serialization.Serializable

@Serializable
data class MovieItem(
    val id: Int,
    val title: String,
    val overview: String
)
