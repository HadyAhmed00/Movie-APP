package io.github.hadyahmed00.movieapp.network

import io.github.hadyahmed00.movieapp.models.Movie
import retrofit2.Response
import retrofit2.http.GET

interface MovieApi {

    @GET("HadyAhmed00/Movie-Static-API/main/Main.json")
    public suspend fun getMovies():Response<List<Movie>>
}