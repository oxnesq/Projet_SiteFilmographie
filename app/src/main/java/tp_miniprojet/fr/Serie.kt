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

    LaunchedEffect(searchQuery.text) {
        if (searchQuery.text==""){
            viewModel.getSeries()
        } else {
            viewModel.searchSeries(searchQuery.text)
        }
    }
    GridObjects(series, navController,"serie",classeLargeur)
}

@Composable
fun SerieDetailsScreen(serieId: Int, navController: NavHostController,classeLargeur: WindowWidthSizeClass) {
    val viewModel: MainViewModel = viewModel()
    val serieDetails by viewModel.serieDetails.collectAsState()

    LaunchedEffect(serieId) {
        viewModel.getSerieDetails(serieId)
    }

    serieDetails?.let { serie ->
        val castMembers = serie.credits.cast.take(9)
        CardDetails(serieDetails, castMembers, navController,"actor",classeLargeur)

    }
}