package tp_miniprojet.fr

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import tp_premiereapplication.fr.R

val posterUrl = "https://image.tmdb.org/t/p/w500"


@Composable
fun GridObjects(list: List<Card>, navController: NavHostController, name: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TitleClass("Films")
        CommonLazyVerticalGridPortrait {
            items(list) { l ->
                CardItem(l, navController, name)
            }
        }
    }
}

@Composable
fun CardItem(card: Card, navController: NavHostController, name: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(340.dp)
            //.padding(8.dp)
            .clickable { navController.navigate(name + "Details/${card.getIdCard()}") },

        ) {
        if (!card.getPosterPathCard().isNullOrEmpty()) {
            AsyncImage(
                model = posterUrl + card.getPosterPathCard(), // L'URL de l'image
                contentDescription = "Poster du film",
            )
        } else {
            Image(
                painter = painterResource(R.drawable.galery),  // Image locale dans drawable
                contentDescription = "Film logo",
                modifier = Modifier
                    .size(50.dp)
            )

        }

        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = card.getTitleCard(),
            style = MaterialTheme.typography.titleMedium.copy(fontSize = 22.sp),
            modifier = Modifier.align(
                Alignment.Start
            )
        )

    }
}


@Composable
fun TitleClass(title: String) {
    Text(
        text = title, // Le titre
        style = MaterialTheme.typography.headlineLarge,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .padding(bottom = 16.dp)
    )
}


@Composable
fun CommonLazyVerticalGridPortrait(
    content: LazyGridScope.() -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2), // Définir 2 colonnes
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp), // Espace entre les colonnes
        content = content
    )
}

@Composable
fun CommonLazyVerticalGridLandscape(
    content: LazyGridScope.() -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3), // Définir 2 colonnes
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp), // Espace entre les colonnes
        content = content
    )
}

