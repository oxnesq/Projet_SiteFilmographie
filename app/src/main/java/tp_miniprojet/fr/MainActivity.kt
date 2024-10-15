package tp_miniprojet.fr

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.*
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable
import tp_premiereapplication.fr.R
import tp_premiereapplication.fr.ui.theme.TP_PremiereApplicationTheme
import androidx.compose.foundation.Image as Image
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.window.core.layout.WindowWidthSizeClass

@Serializable
class Home

@Serializable
class Film()

@Serializable
class Serie()

@Serializable
class Actor()

@Serializable
class FilmDetails()

@Serializable
class ActorDetails()

@Serializable
class serieDetails()

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TP_PremiereApplicationTheme {
                val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                var searchQuery by remember { mutableStateOf(TextFieldValue("")) }
                var isSearching by remember { mutableStateOf(false) }
                val classeLargeur = windowSizeClass.windowWidthSizeClass

                Scaffold(
                    topBar = {
                        when (classeLargeur) {
                            WindowWidthSizeClass.COMPACT -> {
                                if (currentDestination?.hasRoute<Home>() != true) {
                                    TopAppBar(
                                        colors = TopAppBarDefaults.smallTopAppBarColors(
                                            containerColor = Color(0xFF2196F3)
                                        ),
                                        title = {
                                            if (isSearching) {
                                                TextField(
                                                    value = searchQuery,
                                                    onValueChange = { newValue ->
                                                        searchQuery = newValue
                                                    },
                                                    modifier = Modifier
                                                        .fillMaxWidth()
                                                        .padding(horizontal = 8.dp),
                                                    placeholder = { Text(text = "Rechercher...") },
                                                    trailingIcon = {
                                                        if (searchQuery.text.isNotEmpty()) {
                                                            IconButton(onClick = {
                                                                searchQuery = TextFieldValue("")
                                                            }) {
                                                                Icon(
                                                                    imageVector = Icons.Default.Close, // Icône pour effacer la recherche
                                                                    contentDescription = "Clear Icon",
                                                                    tint = Color.Black,
                                                                    modifier = Modifier.size(24.dp)
                                                                )
                                                            }
                                                        }
                                                    },
                                                    colors = TextFieldDefaults.textFieldColors(
                                                        containerColor = Color.Transparent, // Fond transparent
                                                    )
                                                )
                                            } else {
                                                // Si on n'est pas en mode recherche, afficher le titre "Fav'App"
                                                Text(
                                                    "Cine'App",
                                                    maxLines = 1,
                                                    overflow = TextOverflow.Ellipsis,
                                                )
                                            }
                                        },
                                        navigationIcon = {
                                            IconButton(onClick = {
                                                if (findIfDetails(currentDestination)) {
                                                    // Si on est sur une page de détails, naviguer vers la page précédente
                                                    navController.popBackStack()
                                                } else if (isSearching) {

                                                    isSearching = false
                                                    searchQuery =
                                                        TextFieldValue("") // Réinitialiser la recherche
                                                } else {
                                                    isSearching = true
                                                }

                                            }) {
                                                // Afficher la flèche retour si on est en mode recherche, sinon la loupe
                                                if (isSearching || findIfDetails(currentDestination)) {
                                                    Icon(
                                                        imageVector = Icons.Filled.ArrowBack,  // Icône Material Design pour l'email
                                                        contentDescription = "Email",
                                                        modifier = Modifier
                                                            .size(20.dp)  // Ajuster la taille de l'icône
                                                    )
                                                } else {
                                                    Icon(
                                                        painter = painterResource(id = R.drawable.magnifier), // Assurez-vous que l'icône est correcte
                                                        contentDescription = "Rechercher",
                                                        modifier = Modifier.size(24.dp)
                                                    )
                                                }
                                            }
                                        },
                                        actions = {
                                            IconButton(onClick = {
                                                navController.navigate(Home())
                                            }) {
                                                Icon(
                                                    imageVector = Icons.Outlined.Home,  // Icône Material Design pour l'email
                                                    contentDescription = "Home page",
                                                    modifier = Modifier
                                                        .size(28.dp),
                                                    tint = Color.Black
                                                )
                                            }
                                        },
                                    )
                                }
                            }

                            else -> {


                            }
                        }
                    },

                    bottomBar = {
                        when (classeLargeur) {
                            WindowWidthSizeClass.COMPACT -> {
                                if (currentDestination?.hasRoute<Home>() != true) {
                                    NavigationBar(
                                        modifier = Modifier
                                            .height(120.dp),
                                        containerColor = Color(0xFF2196F3),
                                        contentColor = Color.Black,
                                    ) {
                                        NavigationBarItem(
                                            icon = {
                                                NavigationImage(
                                                    currentDestination,
                                                    R.drawable.clapperboard,
                                                    currentDestination?.hasRoute<Film>()
                                                )
                                            },
                                            label = {
                                                Text(
                                                    text = "Films",
                                                )
                                            },
                                            selected = currentDestination?.hasRoute<Film>() == true,
                                            colors = NavigationBarItemDefaults.colors(
                                                indicatorColor = Color(0xFF2264FF)
                                            ),
                                            onClick = { navController.navigate(Film()) })

                                        NavigationBarItem(
                                            icon = {
                                                NavigationImage(
                                                    currentDestination,
                                                    R.drawable.film,
                                                    currentDestination?.hasRoute<Serie>()
                                                )

                                            }, label = { Text("Series") },
                                            selected = currentDestination?.hasRoute<Serie>() == true,
                                            colors = NavigationBarItemDefaults.colors(
                                                indicatorColor = Color(0xFF2264FF)
                                            ),
                                            onClick = { navController.navigate(Serie()) })
                                        NavigationBarItem(
                                            icon = {
                                                NavigationIconActor(currentDestination)
                                            }, label = { Text("Acteurs") },
                                            selected = currentDestination?.hasRoute<Actor>() == true,
                                            colors = NavigationBarItemDefaults.colors(
                                                indicatorColor = Color(0xFF2264FF)
                                            ),
                                            onClick = { navController.navigate(Actor()) })
                                    }
                                }
                            }

                            else -> {}
                        }
                    })
                { innerPadding ->
                    Row {
                        when (classeLargeur) {
                            WindowWidthSizeClass.COMPACT -> {
                                if (currentDestination?.hasRoute<Home>() != true) {
                                    Column() {
                                        NavigationRail(
                                            modifier = Modifier
                                                //.height(50.dp)
                                                .width(100.dp)
                                                .padding(innerPadding),
                                            containerColor = Color(0xFF2196F3),
                                            contentColor = Color.Black
                                        ) {
                                            NavigationRailItem(
                                                icon = {
                                                    NavigationImage(
                                                        currentDestination,
                                                        R.drawable.clapperboard,
                                                        currentDestination?.hasRoute<Film>()
                                                    )
                                                },
                                                label = { Text(text = "Films") },
                                                selected = currentDestination?.hasRoute<Film>() == true,
                                                modifier = Modifier.weight(0.45f),
                                                onClick = { navController.navigate(Film()) }
                                            )

                                            NavigationRailItem(
                                                icon = {
                                                    NavigationImage(
                                                        currentDestination,
                                                        R.drawable.film,
                                                        currentDestination?.hasRoute<Serie>()
                                                    )
                                                }, label = { Text("Series") },
                                                selected = currentDestination?.hasRoute<Serie>() == true,
                                                modifier = Modifier.weight(0.1f),
                                                onClick = { navController.navigate(Serie()) }
                                            )

                                            NavigationRailItem(
                                                icon = { NavigationIconActor(currentDestination) },
                                                label = { Text("Acteurs") },
                                                selected = currentDestination?.hasRoute<Actor>() == true,
                                                modifier = Modifier.weight(0.45f),
                                                onClick = { navController.navigate(Actor()) }
                                            )

                                        }
                                    }
                                }
                            }

                            else -> {}
                        }
                        Column {
                            NavHost(
                                navController = navController, startDestination = Home(),
                                modifier = Modifier.padding(innerPadding)
                            ) {
                                composable<Home> { HomeScreen(windowSizeClass, navController) }
                                composable<Film> {
                                    FilmScreen(
                                        searchQuery,
                                        navController,
                                        windowSizeClass
                                    )
                                }
                                composable<Serie> {
                                    SerieScreen(
                                        searchQuery,
                                        navController,
                                        windowSizeClass
                                    )
                                }
                                composable<Actor> {
                                    ActorScreen(
                                        searchQuery,
                                        navController,
                                        windowSizeClass
                                    )
                                }

                                composable(
                                    "movieDetails/{movieId}",
                                    arguments = listOf(navArgument("movieId") {
                                        type = NavType.IntType
                                    })
                                ) { backStackEntry ->
                                    val movieId = backStackEntry.arguments?.getInt("movieId")
                                    movieId?.let {
                                        FilmDetailsScreen(movieId = it, navController)
                                    }
                                }

                                composable(
                                    "actorDetails/{actorId}",
                                    arguments = listOf(navArgument("actorId") {
                                        type = NavType.IntType
                                    })
                                ) { backStackEntry ->
                                    val actorId = backStackEntry.arguments?.getInt("actorId")
                                    actorId?.let {
                                        ActorDetailsScreen(actorId = it, navController)
                                    }
                                }

                                composable(
                                    "serieDetails/{serieId}",
                                    arguments = listOf(navArgument("serieId") {
                                        type = NavType.IntType
                                    })
                                ) { backStackEntry ->
                                    val serieId = backStackEntry.arguments?.getInt("serieId")
                                    serieId?.let {
                                        SerieDetailsScreen(serieId = it, navController)
                                    }
                                }

                            }
                        }
                    }
                }


            }
        }
    }
}


@Composable
fun NavigationImage(
    currentDestination: NavDestination?,
    logoName: Int,
    currentClass: Boolean?
) {
    Image(
        painter = painterResource(logoName),  // Image locale dans drawable
        contentDescription = "Film logo",
        modifier = Modifier
            .size(20.dp),
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
            .size(20.dp),
        tint = if (currentDestination?.hasRoute<Actor>() == true) Color.Black else Color.DarkGray

    )
}


fun findIfDetails(currentDestination: NavDestination?): Boolean {
    var bo: Boolean
    bo = false
    if (currentDestination?.route?.startsWith("movieDetails") == true ||
        currentDestination?.route?.startsWith("actorDetails") == true ||
        currentDestination?.route?.startsWith("serieDetails") == true
    ) bo = true
    return bo
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

