package tp_miniprojet.fr

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.window.core.layout.WindowWidthSizeClass


@Composable
fun ActorScreen(
    searchQuery: TextFieldValue,
    navController: NavHostController,
    classeLargeur: WindowWidthSizeClass
) {
    val viewModel: MainViewModel = viewModel()
    val actors by viewModel.actors.collectAsState()
    var selectedActor by remember { mutableStateOf<ModelActor?>(null) }
    val actorDetails by viewModel.actorDetails.collectAsState()

    LaunchedEffect(searchQuery.text) {
        if (searchQuery.text == "") {
            viewModel.getActors()
        } else {
            viewModel.searchActors(searchQuery.text)
        }
    }

    GridObjects(actors, navController,"actor", classeLargeur)

}

@Composable
fun ActorDetailsScreen(actorId: Int, navController: NavHostController,classeLargeur: WindowWidthSizeClass) {
    val viewModel: MainViewModel = viewModel()
    val actorDetails by viewModel.actorDetails.collectAsState()

    LaunchedEffect(actorId) {
        viewModel.getActorDetails(actorId)
    }

    actorDetails?.let { actor ->
        val filmography = actor.credits.cast.take(9)
        CardDetails(actorDetails, filmography, navController,"movie",classeLargeur)

    }
}

