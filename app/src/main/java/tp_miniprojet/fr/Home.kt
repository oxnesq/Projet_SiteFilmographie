package tp_miniprojet.fr

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.navigation.NavController
import androidx.window.core.layout.WindowSizeClass
import androidx.window.core.layout.WindowWidthSizeClass
import tp_premiereapplication.fr.R

@Composable
fun HomeScreen(classes: WindowSizeClass, navController: NavController) {
    //val classeHauteur = classes.windowHeightSizeClass
    val classeLargeur = classes.windowWidthSizeClass
    when (classeLargeur) {
        WindowWidthSizeClass.COMPACT -> /* largeur faible */ {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ImageProfile()
                Spacer(modifier = Modifier.height(5.dp))
                Name()
                Spacer(modifier = Modifier.height(15.dp))
                Description()
                Spacer(modifier = Modifier.height(40.dp))
                LogosText()
                Spacer(modifier = Modifier.height(40.dp))
                ButtonSearch(navController)
            }
        }

        else -> {
            Row(
                Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally  // Centre horizontalement les éléments
                ) {
                    ImageProfile()
                    Spacer(modifier = Modifier.height(5.dp))
                    Name()
                    Spacer(modifier = Modifier.height(15.dp))
                    Description()
                }
                Column(
                    modifier = Modifier
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally  // Centre horizontalement les éléments
                ) {
                    LogosText()
                    Spacer(modifier = Modifier.height(40.dp))
                    ButtonSearch(navController)
                }
            }
        }
        /*WindowWidthSizeClass.MEDIUM -> /* largeur moyenne */
        WindowWidthSizeClass.EXPANDED -> /* grande largeur */

         */
    }
}

@Composable
fun ImageProfile() {

    Image(
        painter = painterResource(R.drawable.saut_en_parachute),  // Image locale dans drawable
        contentDescription = "Description de l'image",
        modifier = Modifier
            .clip(CircleShape)
            .size(200.dp)
            .aspectRatio(1f),
        contentScale = ContentScale.Crop
    )
}


@Composable
fun Name() {
    Text(
        text = "Océane Saqué",
        fontWeight = FontWeight.Bold,  // Appliquer le style en gras
        style = MaterialTheme.typography.headlineLarge,
    )
}

@Composable
fun Description() {
    Text(
        text = "Etudiante",
        fontStyle = FontStyle.Italic, // Appliquer le style en gras
        modifier = Modifier
            .padding(3.dp)
    )
    Text(
        text = "Ecole d'ingenieur ISIS - INU Champolion",
        fontStyle = FontStyle.Italic, // Appliquer le style en gras
        modifier = Modifier
            .padding(3.dp)
    )
}

@Composable
fun LogosText() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.CenterHorizontally)
    ) {

        Row(
            modifier = Modifier.align(Alignment.Start)
        ) {
            Icon(
                imageVector = Icons.Filled.Email,
                contentDescription = "Email",
                modifier = Modifier
                    .size(20.dp),
                tint = Color(0xFF0e76a8),

            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = "oceane.saque@etud.univ-jfc.fr",
            )
        }

// Ligne pour le LinkedIn
        Row(
            modifier = Modifier.align(Alignment.Start)
        ) {
            Image(
                painter = painterResource(R.drawable.linkedin),
                contentDescription = "LinkedIn",
                modifier = Modifier
                    .size(20.dp)
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = "www.linkedin.com/in/oceane-saque",
            )
        }
    }
}

@Composable
fun ButtonSearch(navController: NavController) {
    Button(onClick = { navController.navigate(Film()) },
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3))
    ) {
        Text(text = "Démarrer", color = Color.Black)
    }
}


//Spacer(modifier = Modifier.height(5.dp))






