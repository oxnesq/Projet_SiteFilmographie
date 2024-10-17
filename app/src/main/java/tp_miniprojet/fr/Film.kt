package tp_miniprojet.fr

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import tp_premiereapplication.fr.R


@Composable
fun FilmScreen(searchQuery: TextFieldValue, navController: NavHostController) {
    val viewModel: MainViewModel = viewModel()
    val movies by viewModel.movies.collectAsState()


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
        TitleClass("Films")
        CommonLazyVerticalGridPortrait {
            items(movies) { movie ->
                MovieItem(movie, posterUrl,navController )
            }
        }
    }
}


@Composable
fun MovieItem(movie: ModelMovie, posterUrl: String, navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(340.dp)
            //.padding(8.dp)
            .clickable { navController.navigate("movieDetails/${movie.id}")},
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (!movie.poster_path.isNullOrEmpty()){
            AsyncImage(
                model = posterUrl + movie.poster_path, // L'URL de l'image
                contentDescription = "Poster du film",
            )
        } else {
            Image(
                painter = painterResource(R.drawable.galery),  // Image locale dans drawable
                contentDescription = "Film logo",
                modifier = Modifier
                    .size(50.dp)
            )

        }

        Spacer(modifier = Modifier.height(4.dp))
        Text(text = movie.title, style = MaterialTheme.typography.titleMedium.copy(fontSize = 22.sp), modifier = Modifier.align(Alignment.Start))
        Text(text = movie.release_date, style = MaterialTheme.typography.bodySmall, modifier = Modifier.align(Alignment.Start))
    }
}







