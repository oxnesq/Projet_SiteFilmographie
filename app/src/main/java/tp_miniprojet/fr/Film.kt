package tp_miniprojet.fr

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.window.core.layout.WindowWidthSizeClass


@Composable
fun FilmScreen(
    searchQuery: TextFieldValue,
    navController: NavHostController,
    classeLargeur: WindowWidthSizeClass
) {
    val viewModel: MainViewModel = viewModel()
    val movies by viewModel.movies.collectAsState()


    LaunchedEffect(searchQuery.text) {
        if (searchQuery.text == "") {
            viewModel.getMovies()
        } else {
            viewModel.searchMovies(searchQuery.text)
        }

    }


    GridObjects(movies, navController,"movie",classeLargeur)
}








