package tp_miniprojet.fr

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.window.core.layout.WindowWidthSizeClass

@Composable
fun CollectionScreen(
    searchQuery: TextFieldValue, navController: NavHostController,
    classeLargeur: WindowWidthSizeClass,currentDestination: NavDestination?

){

    val viewModel: MainViewModel = viewModel()
    val collections by viewModel.collectionHorror.collectAsState()

    LaunchedEffect(searchQuery.text) {
        if (searchQuery.text == "") {
            viewModel.getCollectionsHorror()
        } else {
            viewModel.getCollections(searchQuery.text)
        }
    }

    GridObjects(collections, navController,"movie",classeLargeur,currentDestination)

/*
    LazyVerticalGrid(
        columns = GridCells.Fixed(2), // Définir 2 colonnes
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp), // Espace entre les colonnes
        ){
        items(collections) { collection ->
            Log.d("CollectionScreen", "Collection Name: ${collection.name}")
            Card(){
                Text(text = collection.name)
            }
        }
    }

 */

}