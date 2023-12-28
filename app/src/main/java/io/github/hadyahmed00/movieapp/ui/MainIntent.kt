package io.github.hadyahmed00.movieapp.ui

sealed class MainIntent {
    object FetchMovies: MainIntent()
}