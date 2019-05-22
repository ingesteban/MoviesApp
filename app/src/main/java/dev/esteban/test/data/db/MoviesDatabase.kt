package dev.esteban.test.data.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dev.esteban.test.data.entities.model.Movies
import dev.esteban.test.presentation.di.TestApp

@Database(entities = [Movies::class], version = 1, exportSchema = false)
abstract class MoviesDatabase : RoomDatabase() {
    abstract fun getMoviesDao(): MoviesDao

    companion object {
        var INSTANCE: MoviesDatabase? = null

        fun getAppDatabase() :MoviesDatabase{
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    TestApp.applicationContext().applicationContext,
                    MoviesDatabase::class.java, "movies_db"
                )
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return  INSTANCE!!
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}
