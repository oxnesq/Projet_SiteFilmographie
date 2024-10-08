package tp_miniprojet.fr

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage

@Composable
fun FilmDetailsScreen(movieId : Int){
    val viewModel: MainViewModel = viewModel()
    val movieDetails by viewModel.movieDetails.collectAsState()
    val scrollState = rememberScrollState()
    val posterUrl = "https://image.tmdb.org/t/p/w500"
    val castMembers = movieDetails!!.credits.cast.take(9)

    LaunchedEffect(movieId) {
        viewModel.getMovieDetails(movieId)
    }


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(scrollState)
    ) {
        if (movieDetails!!.backdrop_path != null) {
            AsyncImage(
                model = posterUrl + movieDetails!!.backdrop_path, // L'URL de l'image
                contentDescription = "Poster du film",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
        }
        Text(
            text = movieDetails!!.title,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(7.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(modifier = Modifier.padding(7.dp)) {
            Column(
                modifier = Modifier
                    //.padding(5.dp)
                    .weight(0.4f) //La colone prendra 40% de la ligne
            ) {
                if (movieDetails!!.poster_path != null) {
                    AsyncImage(
                        model = posterUrl + movieDetails!!.poster_path, // L'URL de l'image
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
                Text(text = movieDetails!!.release_date, style = MaterialTheme.typography.bodyMedium)
                val genreNames = movieDetails!!.genres.joinToString(", ") { it.name }
                Text(text = genreNames, style = MaterialTheme.typography.bodyMedium)
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Synopsis",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(7.dp)
        )
        Text(
            text = movieDetails!!.overview,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(15.dp),
            textAlign = TextAlign.Justify,
        )

        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Têtes d'affiche", style = MaterialTheme.typography.headlineMedium)

/*

        LazyVerticalGrid(
            columns = GridCells.Fixed(3), // Définir 3 colonnes
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp),
            horizontalArrangement = Arrangement.spacedBy(5.dp) // Espace entre les colonnes
        ) {
            items(castMembers) { actor ->

                ActorItem(actor = actor, posterUrl = posterUrl, onClick = {
                    // Action à définir, par exemple afficher les détails de l'acteur
                })
            }
        }*/
    }



}