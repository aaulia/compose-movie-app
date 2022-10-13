package aaulia.compose.movie.data

interface MovieRepository {
    fun getPlayingMovie(page: Int): List<Int>
    fun getPopularMovie(page: Int): List<Int>
    fun getNearingMovie(page: Int): List<Int>
}