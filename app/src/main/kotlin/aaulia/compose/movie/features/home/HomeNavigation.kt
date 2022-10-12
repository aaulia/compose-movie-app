package aaulia.compose.movie.features.home

import aaulia.compose.movie.R
import aaulia.compose.movie.features.home.HomeRoute.*
import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

enum class HomeRoute {
    PLAYING,
    POPULAR,
    NEARING
}

sealed class HomeNavItem(
    val route: HomeRoute,
    @StringRes
    val label: Int,
    val image: ImageVector
) {
    companion object {
        val Default: HomeNavItem by lazy { Playing }

        fun fromRoute(route: HomeRoute): HomeNavItem {
            return when (route) {
                PLAYING -> Playing
                POPULAR -> Popular
                NEARING -> Nearing
            }
        }
    }

    object Playing : HomeNavItem(
        route = PLAYING,
        label = R.string.home_nav_playing,
        image = Icons.Default.PlayArrow
    )

    object Popular : HomeNavItem(
        route = POPULAR,
        label = R.string.home_nav_popular,
        image = Icons.Default.Star
    )

    object Nearing : HomeNavItem(
        route = NEARING,
        label = R.string.home_nav_nearing,
        image = Icons.Default.List
    )
}