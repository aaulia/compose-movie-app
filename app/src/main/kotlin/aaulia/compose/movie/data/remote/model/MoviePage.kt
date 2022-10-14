package aaulia.compose.movie.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MoviePage(
    val page: Int,
    val results: List<MovieSimple>,

    @SerialName("total_pages")
    val totalPages: Int,

    @SerialName("total_results")
    val totalResults: Int
)

val MoviePage.nextPage: Int?
    get() = if (page < totalPages) (page + 1) else null