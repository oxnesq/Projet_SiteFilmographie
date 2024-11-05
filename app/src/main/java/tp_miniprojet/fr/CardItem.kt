package tp_miniprojet.fr

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.window.core.layout.WindowWidthSizeClass
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import tp_premiereapplication.fr.R

val posterUrl = "https://image.tmdb.org/t/p/w500"


@Composable
fun GridObjects(
    list: List<CardType>,
    navController: NavHostController,
    name: String,
    classeLargeur: WindowWidthSizeClass,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        var numberOfMovie = 0
        when (classeLargeur) {
            WindowWidthSizeClass.COMPACT -> {
                numberOfMovie = 2
            }

            else -> {
                numberOfMovie = 5
            }
        }
        CommonLazyVerticalGrid(content = {
            items(list) { l ->
                CardItem(l, navController, name)
            }
        }, numberOfMovie)
    }
}

@Composable
fun CardItem(card: CardType, navController: NavHostController, name: String) {
    Card(elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier.height(300.dp)) {
        Column(
            modifier = Modifier
                .padding(5.dp)
                .clickable { navController.navigate(name + "Details/${card.getIdCard()}") },
        ) {
            if (!card.getPosterPathCard().isNullOrEmpty()) {
                PosterImage(card)
            } else {
                PosterImageEmpty()
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = card.getTitleCard(),
                style = MaterialTheme.typography.titleMedium.copy(fontSize = 22.sp),
                modifier = Modifier.align(
                    Alignment.Start
                )
            )
            Spacer(modifier = Modifier.height(10.dp))
        }
    }

}


@Composable
fun PosterImage(card: CardType) {
    AsyncImage(
        model = posterUrl + card.getPosterPathCard(), // L'URL de l'image
        contentDescription = "Poster du film",
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
    )
}

@Composable
fun PosterImageEmpty() {
    Image(
        painter = painterResource(R.drawable.galery),  // Image locale dans drawable
        contentDescription = "Film logo",
        modifier = Modifier
            .size(50.dp)
    )
}


@Composable
fun CommonLazyVerticalGrid(
    content: LazyGridScope.() -> Unit,
    numberOfMovie: Int
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(numberOfMovie), // Définir 2 colonnes
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp), // Espace entre les colonnes
        content = content,

        )
}

/*
@Composable
fun painterCard(card: Card): Painter {
    Log.i("TESTPainter", "posterPath: ${card.getPosterPathCard()}")
    val painter: Painter
    if (card.getPosterPathCard().isEmpty()) {
        painter=painterResource(id = R.drawable.galery)
    } else {
        painter=rememberAsyncImagePainter(model = "https://image.tmdb.org/t/p/w500${card.getPosterPathCard()}")
    }
    Log.i("TESTPainter", "painter : $painter")
    return painter
}

 */
