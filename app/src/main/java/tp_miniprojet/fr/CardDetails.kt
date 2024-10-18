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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage


@Composable
fun CardDetails(
    card: Card,
    castMembers: List<Card>,
    navController: NavHostController,
    nameClass: String
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(scrollState)
    ) {
        if (card.isAnActor() == false) {
            BackdropImage(card)
            Spacer(modifier = Modifier.height(5.dp))
            TitleCard(card)
            Spacer(modifier = Modifier.height(5.dp))
            PosterAndDetails(card)
        } else {
            ImageForActor(card)
            TitleCard(card)
        }
        Spacer(modifier = Modifier.height(10.dp))
        OverviewCard(card)
        ShowCast(castMembers, navController, nameClass)
    }
}

@Composable
fun ShowCast(castMembers: List<Card>, navController: NavHostController, nameClass: String) {
    Text(
        text = "Têtes d'affiche",
        style = MaterialTheme.typography.headlineMedium,
        fontWeight = FontWeight.SemiBold
    )

    for (itemSeparate in castMembers.chunked(3)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            for (i in itemSeparate)
                Column(
                    modifier = Modifier.weight(0.33f),
                ) {
                    CardItem(i, navController, nameClass)
                }
        }
    }
}

@Composable
fun TitleCard(card: Card) {
    Text(
        text = card.getTitleCard(),
        style = MaterialTheme.typography.headlineLarge,
        modifier = Modifier.padding(7.dp),
        fontWeight = FontWeight.Bold,
    )
}

@Composable
fun BackdropImage(card: Card) {
    if (card.getBackdropPathCard() != null) {
        AsyncImage(
            model = posterUrl + card.getBackdropPathCard(), // L'URL de l'image
            contentDescription = "Poster du film",
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        )
    }
}

@Composable
fun ImageForActor(card: Card) {
    if (card.getPosterPathCard() != null) {
        AsyncImage(
            model = posterUrl + card.getPosterPathCard(), // L'URL de l'image
            contentDescription = "Poster de la Serie",
            modifier = Modifier
                .clip(CircleShape)
                .size(200.dp)
                .aspectRatio(1f),
            //.align(Alignment.CenterHorizontally),
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
}

@Composable
fun PosterAndDetails(card: Card) {
    Row(modifier = Modifier.padding(7.dp)) {
        Column(
            modifier = Modifier
                //.padding(5.dp)
                .weight(0.4f) //La colone prendra 40% de la ligne
        ) {
            if (card.getPosterPathCard().isNotEmpty()) {
                AsyncImage(
                    model = posterUrl + card.getPosterPathCard(), // L'URL de l'image
                    contentDescription = "Poster du film",
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(0.66f) // La largeur 0.66 fois plus grande que la hauteur
                )
            }
        }
        Column(
            modifier = Modifier
                .padding(5.dp)
                .weight(0.6f)
        ) {
            Text(
                text = card.getReleaseDateCard(),
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 18.sp
                )
            )
            val genreNames = card.getGenresCard().joinToString(", ") { it.name }
            Text(
                text = genreNames, style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 18.sp,
                    fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
                )
            )
        }
    }
}

@Composable
fun OverviewCard(card: Card) {
    if (card.getOverviewCard().isNotEmpty()) {
        Text(
            text = "Synopsis",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(7.dp),
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = card.getOverviewCard(),
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(15.dp),
            textAlign = TextAlign.Justify,
        )
        Spacer(modifier = Modifier.height(10.dp))
    }
}