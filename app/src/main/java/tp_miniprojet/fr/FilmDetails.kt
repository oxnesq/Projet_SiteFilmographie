package tp_miniprojet.fr

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController

@Composable
fun FilmDetailsScreen(movieId: Int, navController: NavHostController) {
    val viewModel: MainViewModel = viewModel()
    val movieDetails by viewModel.movieDetails.collectAsState()

    LaunchedEffect(movieId) {
        viewModel.getMovieDetails(movieId)
    }

    movieDetails?.let { movieDetails ->
        val castMembers = movieDetails.credits.cast.take(9)
        CardDetails(movieDetails, castMembers, navController,"actor")
    }
}
