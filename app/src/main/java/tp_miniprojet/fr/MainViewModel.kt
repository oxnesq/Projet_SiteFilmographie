package tp_miniprojet.fr

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainViewModel : ViewModel() {
    val movies = MutableStateFlow<List<ModelMovie>>(listOf())
    val actors = MutableStateFlow<List<ModelActor>>(listOf())
    val series = MutableStateFlow<List<ModelSerie>>(listOf())
    val serieDetails = MutableStateFlow(ModelSerie())
    val movieDetails = MutableStateFlow(ModelMovie())
    val actorDetails = MutableStateFlow(ModelActor())
    val collectionDetails = MutableStateFlow<List<CollectionResult>>(listOf())
    val collectionHorror = MutableStateFlow<List<CollectionResult>>(listOf())
    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build();

    val api = retrofit.create(TmdbApi::class.java)
    val api_key = "b57151d36fecd1b693da830a2bc5766f"

    fun getMovies() {
        viewModelScope.launch {
            movies.value = api.lastmovies(api_key, "fr").results
        }
    }

    fun getActors() {
        viewModelScope.launch {
            actors.value = api.lastactors(api_key, "fr").results
        }
    }

    fun getSeries() {
        viewModelScope.launch {
            series.value = api.lastseries(api_key, "fr").results
        }
    }

    fun searchMovies(searchQuery: String) {
        viewModelScope.launch {
            movies.value = api.searchmovies(api_key, searchQuery, "fr").results
        }
    }

    fun searchSeries(searchQuery: String) {
        viewModelScope.launch {
            series.value = api.searchseries(api_key, searchQuery, "fr").results
        }
    }

    fun searchActors(searchQuery: String) {
        viewModelScope.launch {
            actors.value = api.searchactors(api_key, searchQuery, "fr").results
        }
    }

    fun getActorDetails(id: Int) {
        viewModelScope.launch {
            actorDetails.value = api.actorDetails(id.toString(), api_key, "fr", "credits")
        }
    }

    fun getMovieDetails(id: Int) {
        viewModelScope.launch {
            movieDetails.value = api.movieDetails(id.toString(), api_key, "fr", "credits")
        }
    }

    fun getSerieDetails(id: Int) {
        viewModelScope.launch {
            serieDetails.value = api.serieDetails(id.toString(), api_key, "fr", "credits")
        }
    }

    fun getCollections(text: String) {
        viewModelScope.launch {
            collectionDetails.value = api.searchCollection(text, api_key, "fr").results
        }
    }

    fun getCollectionsHorror() {
        viewModelScope.launch {
            collectionHorror.value = api.searchCollection(api_key ,"horror", "fr").results
        }
    }
}