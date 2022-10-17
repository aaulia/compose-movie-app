package aaulia.compose.movie.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.lang.Integer.max

@Serializable
data class MoviePage(
    val page: Int,
    val results: List<MovieCommon>,

    @SerialName("total_pages")
    val totalPages: Int,

    @SerialName("total_results")
    val totalResults: Int
) {
    companion object {
        const val ITEMS_PER_PAGE = 20
    }
}

val MoviePage.nextPage: Int?
    get() = if (page < totalPages) (page + 1) else null

val MoviePage.prevPage: Int?
    get() = if (page > 1) (page - 1) else null

val MoviePage.itemsBefore: Int
    get() = (page - 1) * MoviePage.ITEMS_PER_PAGE

val MoviePage.itemsAfter: Int
    get() = max(0, totalResults - (itemsBefore + results.size))