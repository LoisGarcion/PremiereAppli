package com.example.premiereappli

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("trending/movie/week")
    suspend fun getMovieList(@Query("api_key") apiKey : String): MovieList

    @GET("movie/{id}?append_to_response=credits")
    suspend fun movieDetails(@Path("id") id: String, @Query("api_key") apiKey : String): Movie

    @GET("search/movie")
    suspend fun searchMovie(@Query("api_key") apiKey : String, @Query("query") query: String): MovieList

    @GET("trending/tv/week")
    suspend fun getSerieList(@Query("api_key") apiKey : String): SerieList

    @GET("tv/{id}?append_to_response=credits")
    suspend fun serieDetails(@Path("id") id: String, @Query("api_key") apiKey : String): Serie

    @GET("search/tv")
    suspend fun searchSerie(@Query("api_key") apiKey : String, @Query("query") query: String): SerieList

    @GET("trending/person/week")
    suspend fun getActorList(@Query("api_key") apiKey : String): ActorList

    @GET("person/{id}?append_to_response=credits")
    suspend fun actorDetails(@Path("id") id: String, @Query("api_key") apiKey : String): Actor

    @GET("search/person")
    suspend fun searchActor(@Query("api_key") apiKey : String, @Query("query") query: String): ActorList

}