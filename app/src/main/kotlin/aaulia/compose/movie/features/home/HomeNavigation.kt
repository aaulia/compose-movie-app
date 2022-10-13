package aaulia.compose.movie.features.home

import aaulia.compose.movie.R
import aaulia.compose.movie.features.home.HomeRoute.*
import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

enum class HomeRoute(
    @StringRes
    val label: Int,
    val image: ImageVector
) {
    PLAYING(
        label = R.string.home_nav_playing,
        image = Icons.Default.PlayArrow
    ),
    POPULAR(
        label = R.string.home_nav_popular,
        image = Icons.Default.Star
    ),
    NEARING(
        label = R.string.home_nav_nearing,
        image = Icons.Default.List
    );

    companion object {
        val DEFAULT = PLAYING
    }
}
