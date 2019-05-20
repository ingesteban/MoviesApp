package dev.esteban.test.data.repository

import dev.esteban.test.data.entities.mappers.MoviesDataMapper
import dev.esteban.test.data.entities.model.ResponseListMovies
import dev.esteban.test.data.db.MoviesDao
import dev.esteban.test.data.db.MoviesDatabase
import io.reactivex.Flowable

class MoviesCacheImpl(
    database: MoviesDatabase,
    private val moviesDataMapper: MoviesDataMapper
) {

    private val dao: MoviesDao = database.getMoviesDao()

    fun getMovies(type:String): Flowable<ResponseListMovies> {
        return dao.getMoviesByType(type).map {
            moviesDataMapper.mapToEntity(it)
        }
    }

    fun saveMovies(it: ResponseListMovies) {
        dao.clear()
        dao.saveAllMovies(it.results)
    }

}