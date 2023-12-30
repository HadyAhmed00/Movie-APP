package io.github.hadyahmed00.movieapp.database

import androidx.room.*
import io.github.hadyahmed00.movieapp.models.Movie
import io.github.hadyahmed00.movieapp.util.Constants
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllMovies(movies: List<Movie>)

    @Query("Select * From ${Constants.MOVIE_TABLE_NAME}")
    fun getAllMovies(): List<Movie>
}