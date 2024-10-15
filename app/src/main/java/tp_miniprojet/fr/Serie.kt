package tp_miniprojet.fr

import coil.compose.AsyncImage
import androidx.compose.foundation.clickable
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
import androidx.window.core.layout.WindowSizeClass
import androidx.window.core.layout.WindowWidthSizeClass


@Composable
fun SerieScreen(
    searchQuery: TextFieldValue,
    navController: NavHostController,
    classes: WindowSizeClass
) {
    val viewModel: MainViewModel = viewModel()
    val series by viewModel.series.collectAsState()
    val posterUrl = "https://image.tmdb.org/t/p/w500"
    var selectedSerie by remember { mutableStateOf<ModelSerie?>(null) }
    val classeLargeur = classes.windowWidthSizeClass


    LaunchedEffect(searchQuery.text) {
        if (searchQuery.text == "") {
            viewModel.getSeries()
        } else {
            viewModel.searchSeries(searchQuery.text)
        }
    }
    when (classeLargeur) {
        WindowWidthSizeClass.COMPACT -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                TitleClass("Serie")

                CommonLazyVerticalGridPortrait{
                    items(series) { serie ->
                        SerieItem(serie, posterUrl, navController)
                    }
                }
            }
        }

        else -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                TitleClass("Serie")

                CommonLazyVerticalGridLandscape {
                    items(series) { serie ->
                        SerieItem(serie, posterUrl, navController)
                    }
                }
            }

        }
    }
}


@Composable
fun SerieItem(serie: ModelSerie, posterUrl: String, navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(350.dp)
            .padding(8.dp)
            .clickable { navController.navigate("serieDetails/${serie.id}") }
    ) {
        if (serie.poster_path != null) {
            AsyncImage(
                model = posterUrl + serie.poster_path,
                contentDescription = "Poster du film",
            )
            Spacer(modifier = Modifier.height(4.dp))
        }
        Text(text = serie.name, style = MaterialTheme.typography.titleLarge)
        Text(text = serie.first_air_date, style = MaterialTheme.typography.bodySmall)
    }
}
