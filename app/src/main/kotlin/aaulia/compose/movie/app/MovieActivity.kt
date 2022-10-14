package aaulia.compose.movie.app

import aaulia.compose.movie.di.Injector
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class MovieActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Injector.init(applicationContext)

        setContent {
            MovieApp()
        }
    }
}
