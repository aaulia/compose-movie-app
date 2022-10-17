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
        const val PAGE_SIZE = 20
        const val PREFETCH_DISTANCE = (PAGE_SIZE * 0.2).toInt()
    }
}

val MoviePage.nextPage: Int?
    get() = if (page < totalPages) (page + 1) else null

val MoviePage.prevPage: Int?
    get() = if (page > 1) (page - 1) else null

val MoviePage.itemsBefore: Int
    get() = (page - 1) * MoviePage.PAGE_SIZE

val MoviePage.itemsAfter: Int
    get() = max(0, totalResults - (itemsBefore + results.size))