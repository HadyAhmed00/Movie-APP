package io.github.hadyahmed00.movieapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import io.github.hadyahmed00.movieapp.models.Movie
import io.github.hadyahmed00.movieapp.util.Constants

@Database(entities = [Movie::class] , version = 1)
abstract class MovieDatabase : RoomDatabase() {
    abstract val movieDao : MovieDao
    companion object {
        @Volatile
        private var instance: MovieDatabase? = null

        fun getDatabaseInstance(context:Context)= instance ?: synchronized(Any()){
            instance ?: createInstance(context).also {
                instance = it
            }
        }

        private fun createInstance (context: Context) = Room.databaseBuilder(
            context,
            MovieDatabase::class.java,
            Constants.DATABASE_NAME).allowMainThreadQueries()
            .fallbackToDestructiveMigration().build()


    }

}