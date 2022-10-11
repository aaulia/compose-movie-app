package aaulia.compose.movie.features.home

import aaulia.compose.movie.R
import aaulia.compose.movie.ui.theme.MovieAppTheme
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController


sealed class HomeNavItem(
    val route: String,
    @StringRes
    val label: Int,
    val image: ImageVector
) {
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


@Composable
fun HomeScreen(
    navController: NavHostController = rememberNavController()
) {
    Scaffold(bottomBar = {
        HomeBottomBar(
            navController = navController,
            items = listOf(
                HomeNavItem.Playing,
                HomeNavItem.Popular,
                HomeNavItem.Nearing,
            )
        )
    }) { paddingValues ->
        NavHost(
            modifier = Modifier.padding(paddingValues),
            navController = navController,
            startDestination = HomeNavItem.Playing.route
        ) {
            composable(HomeNavItem.Playing.route) { }
            composable(HomeNavItem.Popular.route) { }
            composable(HomeNavItem.Nearing.route) { }
        }
    }
}

@Composable
private fun HomeBottomBar(
    navController: NavHostController,
    items: List<HomeNavItem>
) {
    BottomNavigation {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route ?: ""

        for (item in items) {
            BottomNavigationItem(
                selected = (item.route == currentRoute),
                label = { Text(text = stringResource(id = item.label)) },
                icon = { Icon(imageVector = item.image, contentDescription = "") },
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }

                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Preview(device = Devices.NEXUS_5)
@Composable
fun HomeScreenPreview() {
    MovieAppTheme {
        HomeScreen()
    }
}