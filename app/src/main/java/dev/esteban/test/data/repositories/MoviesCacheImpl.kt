package dev.esteban.test.data.repository

import dev.esteban.test.data.db.MoviesDao
import dev.esteban.test.data.db.MoviesDatabase
import dev.esteban.test.data.entities.mappers.MoviesDataMapper
import dev.esteban.test.data.entities.model.ResponseListMovies
import io.reactivex.Flowable

class MoviesCacheImpl(
    private val moviesDataMapper: MoviesDataMapper
) {

    private val dao: MoviesDao = MoviesDatabase.getAppDatabase().getMoviesDao()

    fun getMovies(type:String): Flowable<ResponseListMovies> {
        return dao.getMoviesByType(type).map{
            moviesDataMapper.mapToEntity(it)
        }
    }

    fun saveMovies(it: ResponseListMovies, type:String) {
        dao.clearByType(type)
        dao.saveAllMovies(it.results)
    }

}