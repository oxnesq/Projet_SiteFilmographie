package tp_miniprojet.fr


interface CardType {
    fun isAnActor():Boolean;
    fun getTitleCard(): String;
    fun getIdCard(): Int;
    fun getPosterPathCard(): String;
    fun getBackdropPathCard(): String;
    fun getOverviewCard(): String;
    fun getReleaseDateCard(): String;
    fun getGenresCard():List<Genre>;
    //fun getCreditsCard():Credits;
}


data class ModelListSerie(
    val results: List<ModelSerie> = listOf(),
)

data class ModelSerie(
    val backdrop_path: String = "",
    val credits: Credits = Credits(),
    val genres: List<Genre> = listOf(),
    val id: Int = 0,
    val name: String = "",
    val overview: String = "",
    val popularity: Double = 0.0,
    val poster_path: String = "",
) : CardType {
    override fun isAnActor() = false
    override fun getTitleCard() = name
    override fun getIdCard() = id
    override fun getPosterPathCard() = poster_path
    override fun getBackdropPathCard() = backdrop_path
    override fun getOverviewCard() = overview
    override fun getReleaseDateCard() = ""
    override fun getGenresCard() = genres
   // override fun getCreditsCard() = credits
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
    val character: String = ""
) : CardType {
    override fun isAnActor() = true
    override fun getTitleCard() = name
    override fun getIdCard() = id
    override fun getPosterPathCard() = profile_path
    override fun getBackdropPathCard() = ""
    override fun getOverviewCard() = biography
    override fun getReleaseDateCard() = ""
    override fun getGenresCard(): List<Genre> = listOf()
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
    val title: String = "",
) : CardType {
    override fun isAnActor() = false
    override fun getTitleCard() = title
    override fun getIdCard() = id
    override fun getPosterPathCard() = poster_path
    override fun getBackdropPathCard() = backdrop_path
    override fun getOverviewCard() = overview
    override fun getReleaseDateCard() = release_date
    override fun getGenresCard() = genres
    //override fun getCreditsCard() = credits
}

data class Credits(
    val cast: List<ModelActor> = listOf()
)

data class Genre(
    val id: Int = 0,
    val name: String = ""
)


