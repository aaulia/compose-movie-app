package aaulia.compose.movie.features.home

import aaulia.compose.movie.features.list.ListScreen
import aaulia.compose.movie.features.list.MovieType
import aaulia.compose.movie.ui.theme.MovieAppTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.mapNotNull


@Composable
fun HomeScreen(onMovieClick: (Int) -> Unit = { }) {
    val navController: NavHostController = rememberNavController()
    val currentContext = LocalContext.current


    var topAppBarTitle by remember { mutableStateOf("") }


    LaunchedEffect(navController) {
        navController.currentBackStackEntryFlow
            .mapNotNull { backStackEntry ->
                backStackEntry.destination.route
                    ?.let(HomeRoute::valueOf)
                    ?.let(HomeRoute::label)
                    ?.let(currentContext::getString)
            }
            .collectLatest { topAppBarTitle = it }
    }


    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = topAppBarTitle) })
        },
        bottomBar = {
            HomeBottomBar(
                navController = navController,
                items = listOf(
                    HomeRoute.PLAYING,
                    HomeRoute.POPULAR,
                    HomeRoute.NEARING,
                )
            )
        }) { paddingValues ->
        NavHost(
            modifier = Modifier.padding(paddingValues),
            navController = navController,
            startDestination = "${HomeRoute.DEFAULT}"
        ) {
            composable("${HomeRoute.PLAYING}") { ListScreen(MovieType.PLAYING, onMovieClick) }
            composable("${HomeRoute.POPULAR}") { ListScreen(MovieType.POPULAR, onMovieClick) }
            composable("${HomeRoute.NEARING}") { ListScreen(MovieType.NEARING, onMovieClick) }
        }
    }
}


@Composable
private fun HomeBottomBar(
    navController: NavHostController,
    items: List<HomeRoute>
) {
    BottomNavigation {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
            ?.let(HomeRoute::valueOf)
            ?: HomeRoute.DEFAULT

        for (route in items) {
            BottomNavigationItem(
                selected = route == currentRoute,
                icon = { Icon(imageVector = route.image, contentDescription = "") },
                label = { Text(text = stringResource(id = route.label)) },
                onClick = {
                    navController.navigate("$route") {
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