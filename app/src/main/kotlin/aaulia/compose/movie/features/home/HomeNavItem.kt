package aaulia.compose.movie.features.home

import aaulia.compose.movie.R
import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

sealed class HomeNavItem(
    val route: String,
    @StringRes
    val label: Int,
    val image: ImageVector
) {
    companion object {
        val Default: HomeNavItem by lazy { Playing }

        fun fromRoute(route: String): HomeNavItem? {
            return when (route) {
                "playing" -> Playing
                "popular" -> Popular
                "nearing" -> Nearing
                else -> null
            }
        }
    }

    object Playing : HomeNavItem(
        route = "playing",
        label = R.string.home_nav_playing,
        image = Icons.Default.PlayArrow
    )

    object Popular : HomeNavItem(
        route = "popular",
        label = R.string.home_nav_popular,
        image = Icons.Default.Star
    )

    object Nearing : HomeNavItem(
        route = "nearing",
        label = R.string.home_nav_nearing,
        image = Icons.Default.List
    )
}