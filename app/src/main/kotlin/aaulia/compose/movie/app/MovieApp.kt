package aaulia.compose.movie.app

import aaulia.compose.movie.features.home.HomeScreen
import aaulia.compose.movie.features.info.InfoScreen
import aaulia.compose.movie.ui.theme.MovieAppTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MovieApp() {
    val navController: NavHostController = rememberNavController()

    
    MovieAppTheme {
        NavHost(navController = navController, startDestination = "home") {
            composable("home") {
                HomeScreen(onMovieClick = {
                    navController.navigate("info")
                })
            }

            composable("info") {
                InfoScreen(onNavigateBack = {
                    navController.popBackStack()
                })
            }
        }
    }
}


@Preview(device = Devices.NEXUS_5)
@Composable
fun MovieAppPreview() {
    MovieApp()
}