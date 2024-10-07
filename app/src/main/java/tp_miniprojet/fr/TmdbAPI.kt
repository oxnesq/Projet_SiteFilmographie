package tp_miniprojet.fr

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface TmdbApi {

    // FILMS ACTEURS SERIES DU MOMENT
    @GET("trending/movie/week")
    suspend fun lastmovies(@Query("api_key") api_key: String, @Query("language") language: String): ModelListMovie

    @GET("trending/tv/week")
    suspend fun lastseries(@Query("api_key") api_key: String, @Query("language") language: String): ModelListSerie

    @GET("trending/person/week")
    suspend fun lastactors(@Query("api_key") api_key: String, @Query("language") language: String): ModelListActor

    // CHERCHER UN FILM ACTEUR SERIE
    @GET("movie/{id}")
    suspend fun movieDetails(@Path("id") id: String,@Query("api_key") api_key: String, @Query("language") language: String): ModelMovie

    @GET("person/{id}")
    suspend fun actorDetails(@Path("id") id: String, @Query("api_key") api_key: String,@Query("language") language: String, @Query("append_to_response") appendToResponse: String ): ModelActor

    @GET("tv/{id}")
    suspend fun serieDetails(@Path("id") id: String,@Query("api_key") api_key: String, @Query("language") language: String): ModelSerie

    // CHERCHER DES FILMS ACTEURS SERIES AVEC MOTS CLES
    @GET("search/movie")
    suspend fun searchmovies(@Query("api_key") api_key: String,
                             @Query("query") searchQuery: String, @Query("language") language: String): ModelListMovie

    @GET("search/person")
    suspend fun searchactors(@Query("api_key") api_key: String,
                             @Query("query") searchQuery: String, @Query("language") language: String): ModelListActor

    @GET("search/tv")
    suspend fun searchseries(@Query("api_key") api_key: String,
                             @Query("query") searchQuery: String, @Query("language") language: String): ModelListSerie


    // CHERCHER UN GENRE D UN FILM
    @GET("genre/movie/list")
    suspend fun lastgenres(@Query("api_key") api_key: String, @Query("language") language: String) :ResultListGenre


}