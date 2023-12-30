package io.github.hadyahmed00.movieapp.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.hadyahmed00.movieapp.database.MovieDatabase
import io.github.hadyahmed00.movieapp.models.Movie
import io.github.hadyahmed00.movieapp.models.MoviesRepo
import io.github.hadyahmed00.movieapp.network.RetroInstance
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class MainViewModel(app : Application) : AndroidViewModel(app) {


    private val database = MovieDatabase.getDatabaseInstance(app.applicationContext)
    private val repo = MoviesRepo(database)
    val userIntent = Channel<MainIntent>(Channel.UNLIMITED)

    private val _state: MutableStateFlow<MainState> = MutableStateFlow(MainState.Init)
    val state: StateFlow<MainState> get() = _state

    init {
        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            userIntent.consumeAsFlow().collect {
                when (it) {
                    is MainIntent.FetchMovies -> fetchMovies()
                }
            }
        }
    }

    private fun fetchMovies() {
        viewModelScope.launch {
//
            _state.value = MainState.Loading
            _state.value = try {
                MainState.MoviesData(repo.fitchMovies())
            } catch (e: Exception) {
                MainState.Error(e.stackTraceToString())
            }
        }
    }


}
