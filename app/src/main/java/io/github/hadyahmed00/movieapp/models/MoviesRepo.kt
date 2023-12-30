package io.github.hadyahmed00.movieapp.models

import io.github.hadyahmed00.movieapp.database.MovieDatabase
import io.github.hadyahmed00.movieapp.network.MovieApi
import io.github.hadyahmed00.movieapp.network.RetroInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MoviesRepo (private val movieDB : MovieDatabase) {

    suspend fun fitchMovies(): List<Movie>{

        val movies = RetroInstance.api.getMovies().body()
        movieDB.movieDao.insertAllMovies( movies as List<Movie>)

        return movies
    }


}