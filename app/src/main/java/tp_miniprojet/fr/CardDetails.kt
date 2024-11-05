package tp_miniprojet.fr

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.window.core.layout.WindowWidthSizeClass
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import tp_premiereapplication.fr.R


@Composable
fun CardDetails(
    card: CardType,
    castMembers: List<CardType>,
    navController: NavHostController,
    nameClass: String,
    classeLargeur: WindowWidthSizeClass
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState),
    ) {
        if (card.isAnActor() == false) {
            BackdropImage(card)
            CommonTitle(card.getTitleCard(),fontSize = 40,fontWeight = FontWeight.Bold)
            PosterAndDetails(card)
            OverviewCard(card,"Synopsis")
            CommonTitle("Têtes d'affiche")
        } else {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ImageForActor(card)
                CommonTitle(card.getTitleCard(),fontSize = 40,fontWeight = FontWeight.Bold)
            }
            OverviewCard(card,"Biographie")
            CommonTitle("Filmographie")
        }
        ShowCast(castMembers, navController, nameClass,classeLargeur)
    }
}

@Composable
fun ShowCast(castMembers: List<CardType>, navController: NavHostController, nameClass: String,classeLargeur: WindowWidthSizeClass) {
    var num = 3
    when (classeLargeur) {WindowWidthSizeClass.COMPACT -> {} else->{num=5}}
    for (itemSeparate in castMembers.chunked(num)) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
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
fun BackdropImage(card: CardType) {
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
fun ImageForActor(card: CardType) {

    if (card.getPosterPathCard() != null) {
        AsyncImage(
            model = posterUrl + card.getPosterPathCard(), // L'URL de l'image
            contentDescription = "Portrait de l'acteur",
            modifier = Modifier
                .clip(CircleShape)
                .size(200.dp)
                .aspectRatio(1f),
            contentScale = ContentScale.Crop
        )
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
fun PosterAndDetails(card: CardType) {
    Row(modifier = Modifier.padding(7.dp)) {
        Column(
            modifier = Modifier
                //.padding(5.dp)
                .weight(0.4f) //La colone prendra 40% de la ligne
        ) {
           PosterImage(card)
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
fun OverviewCard(card: CardType, title: String) {
    if (card.getOverviewCard().isNotEmpty()) {
        CommonTitle(title)
        Text(
            text = card.getOverviewCard(),
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(15.dp),
            textAlign = TextAlign.Justify,
        )
        Spacer(modifier = Modifier.height(10.dp))
    }
}

@Composable
fun CommonTitle(text: String, modifier: Modifier = Modifier, fontSize: Int = 30, fontWeight: FontWeight = FontWeight.SemiBold) {
    Text(
        text = text,
        style = MaterialTheme.typography.headlineLarge.copy(fontSize = fontSize.sp),
        fontWeight = fontWeight,
        modifier = modifier.padding(7.dp)
    )
}
