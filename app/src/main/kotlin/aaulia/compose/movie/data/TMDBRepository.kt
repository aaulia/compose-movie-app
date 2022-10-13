package aaulia.compose.movie.data

class TMDBRepository: MovieRepository {
    override fun getPlayingMovie(page: Int): List<Int> = getData(page)
    override fun getPopularMovie(page: Int): List<Int> = getData(page)
    override fun getNearingMovie(page: Int): List<Int> = getData(page)

    private fun getData(page: Int) =
        (((page - 1) * 10)..(page * 10)).toList()
}