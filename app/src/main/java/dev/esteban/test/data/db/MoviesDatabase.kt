package dev.esteban.test.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.esteban.test.data.entities.model.Movies

@Database(entities = [Movies::class], version = 1, exportSchema = false)
abstract class MoviesDatabase : RoomDatabase() {
    abstract fun getMoviesDao(): MoviesDao
}