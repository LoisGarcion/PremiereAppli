package com.example.premiereappli

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainViewModel : ViewModel() {

    val apiKey = "b794611072076beee98259b0e9c6f2bb";

    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build();

    val api = retrofit.create(Api::class.java)

    val movies = MutableStateFlow<MovieList>(MovieList())

    val movie = MutableStateFlow<Movie>(Movie())

    val series = MutableStateFlow<SerieList>(SerieList())

    val serie = MutableStateFlow<Serie>(Serie())

    val actors = MutableStateFlow<ActorList>(ActorList())

    val actor = MutableStateFlow<Actor>(Actor())

    fun getMovies(){
        viewModelScope.launch {
            movies.value = api.getMovieList(apiKey)
        }
    }

    fun getMoviesBySearch(keyword : String){
        viewModelScope.launch {
            movies.value = api.searchMovie(apiKey, keyword)
        }
    }

    fun getMovieDetails(id : String){
        viewModelScope.launch {
            movie.value = api.movieDetails(apiKey, id)
        }
    }

    fun getSeries(){
        viewModelScope.launch {
            series.value = api.getSerieList(apiKey)
        }
    }

    fun getSeriesBySearch(keyword : String){
        viewModelScope.launch {
            series.value = api.searchSerie(apiKey, keyword)
        }
    }

    fun getSerieDetails(id : String){
        viewModelScope.launch {
            serie.value = api.serieDetails(apiKey, id)
        }
    }

    fun getActors(){
        viewModelScope.launch {
            actors.value = api.getActorList(apiKey)
        }
    }

    fun getActorsBySearch(keyword : String){
        viewModelScope.launch {
            actors.value = api.searchActor(apiKey, keyword)
        }
    }

    fun getActorDetails(id : String){
        viewModelScope.launch {
            actor.value = api.actorDetails(apiKey, id)
        }
    }
}