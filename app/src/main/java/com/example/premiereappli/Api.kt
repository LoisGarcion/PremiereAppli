package com.example.premiereappli

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("trending/movie/week")
    suspend fun getMovieList(@Query("api_key") apiKey : String): MovieList

    @GET("movie/{movie_id}")
    suspend fun movieDetails(@Query("api_key") apiKey : String, @Path("id") id: String): Movie

    @GET("search/movie")
    suspend fun searchMovie(@Query("api_key") apiKey : String, @Query("query") query: String): MovieList

    @GET("trending/tv/week")
    suspend fun getSerieList(@Query("api_key") apiKey : String): SerieList

    @GET("tv/{tv_id}")
    suspend fun serieDetails(@Query("api_key") apiKey : String, @Path("id") id: String): Serie

    @GET("search/tv")
    suspend fun searchSerie(@Query("api_key") apiKey : String, @Path("id") id: String): SerieList

    @GET("trending/person/week")
    suspend fun getActorList(@Query("api_key") apiKey : String): ActorList

    @GET("person/{person_id}")
    suspend fun actorDetails(@Query("api_key") apiKey : String, @Path("id") id: String): Actor

    @GET("search/person")
    suspend fun searchActor(@Query("api_key") apiKey : String, @Path("id") id: String): ActorList

}