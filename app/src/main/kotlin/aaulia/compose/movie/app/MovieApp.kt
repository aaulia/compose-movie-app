package aaulia.compose.movie.app

import aaulia.compose.movie.features.home.HomeScreen
import aaulia.compose.movie.features.info.InfoScreen
import aaulia.compose.movie.ui.theme.MovieAppTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun MovieApp() {
    val navController: NavHostController = rememberNavController()


    MovieAppTheme {
        NavHost(navController = navController, startDestination = "home") {
            composable(
                route = "home",
                arguments = emptyList()
            ) {
                HomeScreen(onMovieClick = { movieId ->
                    navController.navigate("info/$movieId")
                })
            }

            composable(
                route = "info/{movieId}",
                arguments = listOf(navArgument("movieId") { type = NavType.IntType })
            ) {
                it.arguments?.getInt("movieId")
                    ?.let { _ ->
                        InfoScreen(onNavigateBack = {
                            navController.popBackStack()
                        })
                    }
            }
        }
    }
}


@Preview(device = Devices.NEXUS_5)
@Composable
fun MovieAppPreview() {
    MovieApp()
}