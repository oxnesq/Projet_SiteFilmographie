package tp_miniprojet.fr

import androidx.compose.foundation.clickable
import coil.compose.AsyncImage
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController


@Composable
fun FilmScreen(searchQuery: TextFieldValue, navController: NavHostController) {
    val viewModel: MainViewModel = viewModel()
    val movies by viewModel.movies.collectAsState()
    val posterUrl = "https://image.tmdb.org/t/p/w500"


    LaunchedEffect(searchQuery.text) {
        if (searchQuery.text == "") {
            viewModel.getMovies()
        } else {
            viewModel.searchMovies(searchQuery.text)
        }

    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Films", // Le titre
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(bottom = 16.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2), // Définir 2 colonnes
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp) // Espace entre les colonnes
        ) {
            items(movies) { movie ->
                MovieItem(movie, posterUrl,navController )
            }
        }
    }
}


@Composable
fun MovieItem(movie: ModelMovie, posterUrl: String, navController: NavHostController) {
    // Affichage d'un élément de film avec son titre et son synopsis
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(330.dp)
            .padding(8.dp)
            .clickable { navController.navigate("movieDetails/{movieId}")}
    ) {
        AsyncImage(
            model = posterUrl + movie.poster_path, // L'URL de l'image
            contentDescription = "Poster du film",
        )

        Spacer(modifier = Modifier.height(4.dp))
        Text(text = movie.title, style = MaterialTheme.typography.titleLarge)
        Text(text = movie.release_date, style = MaterialTheme.typography.bodySmall)
    }
}







