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
    val genres = MutableStateFlow<List<Genre>>(listOf())
    private val _actorDetails = MutableStateFlow<ModelActor?>(null)
    val actorDetails: StateFlow<ModelActor?> get() = _actorDetails
    private val _movieDetails = MutableStateFlow<ModelMovie?>(null)
    val movieDetails: StateFlow<ModelMovie?> get() = _movieDetails


    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build();

    val api = retrofit.create(TmdbApi::class.java)
    val api_key= "b57151d36fecd1b693da830a2bc5766f"

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

    fun searchMovies(searchQuery: String){
        viewModelScope.launch {
            movies.value = api.searchmovies(api_key, searchQuery, "fr").results
        }
    }

    fun searchSeries(searchQuery: String){
        viewModelScope.launch {
            series.value = api.searchseries(api_key, searchQuery, "fr").results
        }
    }

    fun searchActors(searchQuery: String){
        viewModelScope.launch {
            actors.value = api.searchactors(api_key, searchQuery, "fr").results
        }
    }

    fun getActorDetails(id: Int){
        viewModelScope.launch {
            val actorDetailResponse = api.actorDetails(id.toString(),api_key,"fr","credits")
            _actorDetails.value = actorDetailResponse
        }
    }

    fun getMovieDetails(id: Int){
        viewModelScope.launch {
            val movieDetailResponse = api.movieDetails(id.toString(),api_key,"fr","credits")
            _movieDetails.value = movieDetailResponse
        }
    }


}