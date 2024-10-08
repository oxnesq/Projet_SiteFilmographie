package tp_miniprojet.fr

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import coil.compose.AsyncImage
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.rounded.Person
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun ActorScreen(searchQuery: TextFieldValue) {
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


    if (selectedActor != null) {
        LaunchedEffect(selectedActor) {
            viewModel.getActorDetails(selectedActor!!.id)

        }
        actorDetails?.let { ActorDescription(it, posterUrl) }
    } else {
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
                    ActorItem(actor, posterUrl, onClick = { selectedActor = actor })

                }
            }
        }
    }
}

@Composable
fun ActorItem(actor: ModelActor, posterUrl: String, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            //.height(330.dp)
            .padding(8.dp)
            .clickable { onClick() }
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


@Composable
fun ActorDescription(actor: ModelActor, posterUrl: String) {
    val scrollState = rememberScrollState()
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
            modifier = Modifier.align(Alignment.CenterHorizontally),
        )
        Spacer(modifier = Modifier.height(14.dp))
        Text(text = "Biographie", style = MaterialTheme.typography.titleLarge)
        Text(
            text = actor.biography,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Justify,
        )

        Spacer(modifier = Modifier.height(14.dp))
        Text(text = "Filmographie", style = MaterialTheme.typography.titleLarge)


    }
}


