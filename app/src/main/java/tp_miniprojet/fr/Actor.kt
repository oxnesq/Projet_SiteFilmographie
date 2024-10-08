package tp_miniprojet.fr

import androidx.compose.foundation.clickable
import coil.compose.AsyncImage
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController


@Composable
fun ActorScreen(searchQuery: TextFieldValue, navController: NavHostController) {
    val viewModel: MainViewModel = viewModel()
    val actors by viewModel.actors.collectAsState()
    val posterUrl = "https://image.tmdb.org/t/p/w500"
    var selectedActor by remember { mutableStateOf<ModelActor?>(null) }
    val actorDetails by viewModel.actorDetails.collectAsState()

    LaunchedEffect(searchQuery.text) {
        if (searchQuery.text == "") {
            viewModel.getActors()
        } else {
            viewModel.searchActors(searchQuery.text)
        }
    }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Acteurs", // Le titre
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
            items(actors) { actor ->
                ActorItem(actor, posterUrl,navController)

            }
        }
    }
}


@Composable
fun ActorItem(actor: ModelActor, posterUrl: String, navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            //.height(330.dp)
            //.padding(8.dp)
            .clickable { navController.navigate("actorDetails/${actor.id}") }
    ) {
        if (actor.profile_path != null) {
            AsyncImage(
                model = posterUrl + actor.profile_path,
                contentDescription = "Poster du film",
                modifier = Modifier.clip(CircleShape),
                //contentScale = ContentScale.Crop
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
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}


