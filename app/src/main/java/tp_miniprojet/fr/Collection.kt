package tp_miniprojet.fr

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlin.math.log

@Composable
fun CollectionScreen(searchQuery: TextFieldValue,
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


    if (collections.isEmpty()) {
        Text(text = "No collections available.")
        return
    }


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

}