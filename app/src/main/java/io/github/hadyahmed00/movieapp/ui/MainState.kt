package io.github.hadyahmed00.movieapp.ui

import io.github.hadyahmed00.movieapp.models.Movie

sealed class MainState {

    object Init: MainState()
    object Loading: MainState()
    data class MoviesData(val movies: List<Movie>): MainState()
    data class Error(val error: String?): MainState()

}