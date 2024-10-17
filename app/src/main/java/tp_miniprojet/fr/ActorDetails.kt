package tp_miniprojet.fr

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage

@Composable
fun ActorDetailsScreen(actorId: Int, navController: NavHostController) {
    val viewModel: MainViewModel = viewModel()
    val actorDetails by viewModel.actorDetails.collectAsState()
    val scrollState = rememberScrollState()
    val posterUrl = "https://image.tmdb.org/t/p/w500"

    LaunchedEffect(actorId) {
        viewModel.getActorDetails(actorId)
    }

    actorDetails?.let { actor ->
        val filmography = actor.credits.cast.take(9)

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(7.dp)
                .verticalScroll(scrollState),
        ) {
            if (actor.profile_path != null) {
                AsyncImage(
                    model = posterUrl + actor.profile_path, // L'URL de l'image
                    contentDescription = "Poster de la Serie",
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(200.dp)
                        .aspectRatio(1f)
                        .align(Alignment.CenterHorizontally),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(4.dp))
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = "Personne sans photo",
                        modifier = Modifier
                            .size(50.dp),
                        tint = Color.Gray
                    )
                }
            }

            Text(
                text = actor.name,
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterHorizontally),
            )
            Spacer(modifier = Modifier.height(14.dp))
            if (actor.biography.isNotEmpty()){
                Text(text = "Biographie", style = MaterialTheme.typography.titleLarge,fontWeight = FontWeight.SemiBold)
                Text(
                    text = actor.biography,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Justify,
                )
            }

            Spacer(modifier = Modifier.height(14.dp))
            Text(text = "Filmographie", style = MaterialTheme.typography.titleLarge,fontWeight = FontWeight.SemiBold)

            for(separate in filmography.chunked(3)){
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ){
                    for (s in separate)
                        Column (
                            modifier = Modifier.weight(0.33f),
                        )  {
                            MovieItem(movie = s, posterUrl = posterUrl, navController)
                        }
                }
            }

        }
    }
}

