package dev.esteban.test.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.esteban.test.data.entities.model.Movies
import io.reactivex.Flowable

@Dao
interface MoviesDao{

    @Query("Select * from movies  WHERE type > :type")
    fun getMoviesByType(type:String): Flowable<List<Movies>?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllMovies(movies: List<Movies>)

    @Query("DELETE FROM movies")
    fun clear()

}
