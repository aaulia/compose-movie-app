package aaulia.compose.movie.features.home

import aaulia.compose.movie.features.list.List
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
fun HomeScreen(
    navController: NavHostController = rememberNavController()
) {
    var topAppBarTitle by remember { mutableStateOf("") }


    val currentContext = LocalContext.current
    LaunchedEffect(navController) {
        navController.currentBackStackEntryFlow
            .mapNotNull { backStackEntry ->
                backStackEntry.destination.route
                    ?.let(HomeRoute::valueOf)
                    ?.let(HomeNavItem.Companion::fromRoute)
                    ?.let(HomeNavItem::label)
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
                    HomeNavItem.Playing,
                    HomeNavItem.Popular,
                    HomeNavItem.Nearing,
                )
            )
        }) { paddingValues ->
        NavHost(
            modifier = Modifier.padding(paddingValues),
            navController = navController,
            startDestination = "${HomeRoute.PLAYING}"
        ) {
            composable("${HomeRoute.PLAYING}") { List(MovieType.PLAYING) }
            composable("${HomeRoute.POPULAR}") { List(MovieType.POPULAR) }
            composable("${HomeRoute.NEARING}") { List(MovieType.NEARING) }
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
        val currentRoute = navBackStackEntry?.destination?.route?.let(HomeRoute::valueOf)

        for (item in items) {
            BottomNavigationItem(
                selected = currentRoute?.equals(item.route) ?: false,
                icon = { Icon(imageVector = item.image, contentDescription = "") },
                label = { Text(text = stringResource(id = item.label)) },
                onClick = {
                    navController.navigate("${item.route}") {
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