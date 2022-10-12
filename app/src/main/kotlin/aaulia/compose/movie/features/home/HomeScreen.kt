package aaulia.compose.movie.features.home

import aaulia.compose.movie.ui.theme.MovieAppTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map


@Composable
fun HomeScreen(
    navController: NavHostController = rememberNavController()
) {
    var topAppBarTitle by remember { mutableStateOf("") }


    val currentContext = LocalContext.current
    LaunchedEffect(navController) {
        navController.currentBackStackEntryFlow
            .map { HomeNavItem.fromRoute(it.destination.route.orEmpty()) ?: HomeNavItem.Default }
            .collectLatest { currentNavItem ->
                topAppBarTitle = currentContext.getString(currentNavItem.label)
            }
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
            startDestination = HomeNavItem.Default.route
        ) {
            composable(HomeNavItem.Playing.route) { MovieList() }
            composable(HomeNavItem.Popular.route) { MovieList() }
            composable(HomeNavItem.Nearing.route) { MovieList() }
        }
    }
}

@Composable
fun MovieList() {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(20) { index ->
            MovieItem(index)
        }
    }
}

@Composable
fun MovieItem(index: Int) {
    Surface(
        modifier = Modifier
            .safeContentPadding()
            .height(192.dp),
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colors.secondary
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "$index")
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
        val currentRoute = navBackStackEntry?.destination?.route

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