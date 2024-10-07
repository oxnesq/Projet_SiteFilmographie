package tp_miniprojet.fr

import androidx.compose.foundation.BorderStroke
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items

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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel



@Composable
fun SerieScreen(searchQuery: TextFieldValue) {
    val viewModel: MainViewModel = viewModel()
    val series by viewModel.series.collectAsState()
    val posterUrl = "https://image.tmdb.org/t/p/w500"
    var selectedSerie by remember { mutableStateOf<ResultListSerie?>(null) }

    LaunchedEffect(searchQuery.text) {
        if (searchQuery.text==""){
            viewModel.getSeries()
        } else {
            viewModel.searchSeries(searchQuery.text)
        }
    }

    if (selectedSerie!=null){
        SerieDescription(selectedSerie!!,posterUrl)
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "Series", // Le titre
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
                items(series) { serie ->
                    SerieItem(serie, posterUrl, onClick = { selectedSerie = serie })
                }
            }
        }
    }
}


@Composable
fun SerieItem(serie: ResultListSerie, posterUrl: String, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(350.dp)
            .padding(8.dp)
            .clickable { onClick() }
    ) {
        if (serie.poster_path != null) {
            AsyncImage(
                model = posterUrl + serie.poster_path,
                contentDescription = "Poster du film",
            )
            Spacer(modifier = Modifier.height(4.dp))
        }
        Text(text = serie.name, style = MaterialTheme.typography.titleLarge)
        Text(text= serie.first_air_date, style=MaterialTheme.typography.bodySmall)
    }
}


@Composable
fun SerieDescription(serie : ResultListSerie, posterUrl: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(330.dp)
            .padding(8.dp)
    ) {
        Text(text = serie.name, style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(4.dp))
        Row {
            AsyncImage(
                model = posterUrl + serie.poster_path, // L'URL de l'image
                contentDescription = "Poster de la Serie",
            )
            //Text(text= serie.release_date, style=MaterialTheme.typography.bodySmall)

        }

    }
}