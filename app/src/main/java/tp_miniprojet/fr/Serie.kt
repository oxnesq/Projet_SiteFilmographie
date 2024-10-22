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
fun SerieScreen(
    searchQuery: TextFieldValue,
    navController: NavHostController,
    classeLargeur: WindowWidthSizeClass
) {
    val viewModel: MainViewModel = viewModel()
    val series by viewModel.series.collectAsState()
    var selectedSerie by remember { mutableStateOf<ModelSerie?>(null) }

    LaunchedEffect(searchQuery.text) {
        if (searchQuery.text==""){
            viewModel.getSeries()
        } else {
            viewModel.searchSeries(searchQuery.text)
        }
    }
    GridObjects(series, navController,"tv",classeLargeur)
}
