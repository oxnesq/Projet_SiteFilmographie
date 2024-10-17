package tp_miniprojet.fr


interface Card {
    fun getTitleCard() : String;
    fun getIdCard() : Int;
    fun getPosterPathCard() : String;
    fun getOverviewCard() : String;

}


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
) : Card{
    override fun getTitleCard() = name
    override fun getIdCard() = id
    override fun getPosterPathCard() = poster_path
    override fun getOverviewCard() = overview
}


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
): Card{
    override fun getTitleCard() = name
    override fun getIdCard() = id
    override fun getPosterPathCard() = profile_path
    override fun getOverviewCard() = biography
}


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
) : Card{
    override fun getTitleCard() = title
    override fun getIdCard() = id
    override fun getPosterPathCard() = poster_path
    override fun getOverviewCard() = overview
}

data class Credits(
    val cast: List<ModelActor> = listOf()
)

data class Genre(
    val id: Int = 0,
    val name: String = ""
)


