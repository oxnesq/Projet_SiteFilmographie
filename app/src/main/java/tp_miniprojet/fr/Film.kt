package tp_miniprojet.fr

import androidx.compose.foundation.clickable
import coil.compose.AsyncImage
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun FilmScreen(searchQuery: TextFieldValue) {
    val viewModel: MainViewModel = viewModel()
    val movies by viewModel.movies.collectAsState()
    val genres by viewModel.genres.collectAsState()
    val posterUrl = "https://image.tmdb.org/t/p/w500"
    var selectedMovie by remember { mutableStateOf<ResultListMovie?>(null) }

    LaunchedEffect(searchQuery.text) {
        if (searchQuery.text == "") {
            viewModel.getMovies()
        } else {
            viewModel.searchMovies(searchQuery.text)
        }
        viewModel.getGenres()
        //for (m in movies)
            //viewModel.getCast(m.id)
    }

    if (selectedMovie != null) {
        MovieDescription(selectedMovie!!, posterUrl, genres)
    } else {

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
                    MovieItem(movie, posterUrl, onClick = { selectedMovie = movie })
                }
            }
        }
    }

}

@Composable
fun MovieItem(movie: ResultListMovie, posterUrl: String, onClick: () -> Unit) {
    // Affichage d'un élément de film avec son titre et son synopsis
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(330.dp)
            .padding(8.dp)
            .clickable { onClick() }
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


@Composable
fun MovieDescription(
    movie: ResultListMovie,
    posterUrl: String,
    genres: List<Genre>,
    //actors: ResultListActor,
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(scrollState)
    ) {
        if (movie.backdrop_path != null) {
            AsyncImage(
                model = posterUrl + movie.backdrop_path, // L'URL de l'image
                contentDescription = "Poster du film",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
        }
        Text(text = movie.title, style = MaterialTheme.typography.headlineLarge, modifier = Modifier.padding(7.dp))
        Spacer(modifier = Modifier.height(10.dp))
        Row (modifier = Modifier.padding(7.dp)){
            Column(
                modifier = Modifier
                    //.padding(5.dp)
                    .weight(0.4f) //La colone prendra 40% de la ligne
            ) {
                if (movie.poster_path != null) {
                    AsyncImage(
                        model = posterUrl + movie.poster_path, // L'URL de l'image
                        contentDescription = "Poster du film",
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(0.66f) // La largeur 0.66 fois plus grande que la hauteur
                    )
                }
            }
            Column(
                modifier = Modifier
                    .padding(5.dp)
                    .weight(0.6f)
            ) {
                Text(text = movie.release_date, style = MaterialTheme.typography.bodyMedium)
                Text(
                    text = getGenreNames(movie.genre_ids, genres),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Synopsis", style = MaterialTheme.typography.headlineMedium, modifier = Modifier.padding(7.dp))
        Text(text = movie.overview, style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(15.dp), textAlign = TextAlign.Justify,)

        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Têtes d'affiche", style = MaterialTheme.typography.headlineMedium)


    }

}


fun getGenreNames(genreIds: List<Int>, genres: List<Genre>): String {
    if (genreIds.isEmpty() || genres.isEmpty()) {
        return "Genres non disponibles"
    }

    return genreIds.mapNotNull { id ->
        genres.find { it.id == id }?.name
    }.joinToString(", ")
}
