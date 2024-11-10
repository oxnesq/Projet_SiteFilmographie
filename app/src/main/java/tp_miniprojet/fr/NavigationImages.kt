package tp_miniprojet.fr

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute


@Composable
fun NavigationImage(
    currentDestination: NavDestination?,
    logoName: Int,
    currentClass: Boolean?) {
    Image(
        painter = painterResource(logoName),  // Image locale dans drawable
        contentDescription = "Nav logo",
        modifier = Modifier
            .size(30.dp),
        colorFilter = if (currentClass == true) {
            ColorFilter.tint(Color.Black) // Couleur de l'icône quand sélectionné
        } else {
            ColorFilter.tint(Color.DarkGray) // Couleur de l'icône quand non sélectionné
        }
    )
}

@Composable
fun NavigationIconActor(currentDestination: NavDestination?) {
    Icon(
        imageVector = Icons.Filled.Person,  // Icône Material Design pour l'email
        contentDescription = "Actors",
        modifier = Modifier
            .size(30.dp),
        tint = if (currentDestination?.hasRoute<Actor>() == true) Color.Black else Color.DarkGray
    )
}

@Composable
fun CommonIcon(logoName: ImageVector) {
    Icon(
        imageVector = logoName,
        contentDescription = "Icon",
        modifier = Modifier
            .size(30.dp),
tint = Color.Black
    )
}