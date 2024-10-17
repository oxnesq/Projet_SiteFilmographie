package tp_miniprojet.fr

data class ModelListSerie(
    val results: List<ModelSerie> = listOf(),
)

data class ModelSerie(
    val backdrop_path: String = "",
    val credits: Credits = Credits() ,
    val genres: List<Genre> = listOf(),
    val id: Int = 0,
    val name: String = "",
    val overview: String = "",
    val popularity: Double = 0.0,
    val poster_path: String = "",
)



data class ModelListActor(
    val results: List<ModelActor> = listOf(),
)

data class ModelActor(
    val biography: String = "",
    val credits: CreditsActor = CreditsActor(),
    val id: Int = 0,
    val name: String = "",
    val popularity: Double = 0.0,
    val profile_path: String = "",
    val character: String =""
)


data class CreditsActor(
    val cast: List<ModelMovie> = listOf(),
)

data class ModelListMovie(
    val results: List<ModelMovie> = listOf(),
)

data class ModelMovie(
    val backdrop_path: String = "",
    val credits: Credits = Credits(),
    val genres: List<Genre> = listOf(),
    val id: Int = 0,
    val overview: String = "",
    val poster_path: String = "",
    val release_date: String = "",
    val title: String="",
)

data class Credits(
    val cast: List<ModelActor> = listOf()
)

data class Genre(
    val id: Int = 0,
    val name: String = ""
)


