package tp_miniprojet.fr

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage

@Composable
fun SerieDetailsScreen(serieId: Int, navController: NavHostController) {
    val viewModel: MainViewModel = viewModel()
    val serieDetails by viewModel.serieDetails.collectAsState()
    val scrollState = rememberScrollState()
    val posterUrl = "https://image.tmdb.org/t/p/w500"

    LaunchedEffect(serieId) {
        viewModel.getSerieDetails(serieId)
    }

    serieDetails?.let { serie ->
        val castMembers = serie.credits.cast.take(9)

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(scrollState)
        ) {
            if (serie.backdrop_path != null) {
                AsyncImage(
                    model = posterUrl + serie.backdrop_path, // L'URL de l'image
                    contentDescription = "Poster du film",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))
            }
            Text(
                text = serie.name,
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(7.dp),
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(modifier = Modifier.padding(7.dp)) {
                Column(
                    modifier = Modifier
                        //.padding(5.dp)
                        .weight(0.4f) //La colone prendra 40% de la ligne
                ) {
                    if (serie.poster_path.isNotEmpty()) {
                        AsyncImage(
                            model = posterUrl + serie.poster_path, // L'URL de l'image
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

                    val genreNames = serie.genres.joinToString(", ") { it.name }
                    Text(text = genreNames, style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 18.sp,
                        fontStyle = androidx.compose.ui.text.font.FontStyle.Italic))
                }
            }
            Spacer(modifier = Modifier.height(10.dp))

            if (serie.overview.isNotEmpty()){
                Text(
                    text = "Synopsis",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(7.dp),
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = serie.overview,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(15.dp),
                    textAlign = TextAlign.Justify,
                )
                Spacer(modifier = Modifier.height(10.dp))
            }


            Text(text = "Têtes d'affiche", style = MaterialTheme.typography.headlineMedium,fontWeight = FontWeight.SemiBold)

            for(separate in castMembers.chunked(3)){
                Row (
                    modifier = Modifier.fillMaxWidth()
                        .padding(7.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ){
                    for (s in separate)
                        Column (
                            modifier = Modifier.weight(0.33f),
                        )  {
                            ActorItem(actor = s, posterUrl = posterUrl, navController)
                        }
                }
            }

        }
    }
}