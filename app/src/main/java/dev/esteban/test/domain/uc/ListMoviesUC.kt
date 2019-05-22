package dev.esteban.test.domain.uc

import dev.esteban.test.data.entities.model.ResponseListMovies
import dev.esteban.test.domain.repositories.MoviesRepository
import io.reactivex.Flowable

/**
 * Created by Esteban Barrios on 14/05/2019.
 */
class ListMoviesUC(
    private val moviesRepository: MoviesRepository
) {

    /**
     * Retorna el Observable encargado de realizar la petición Http al servicio de estrenos
     */
    fun getLocalMovies(type:String): Flowable<ResponseListMovies> {
        return moviesRepository.getLocalMovies(type)
    }

    /**
     * Retorna el Observable encargado de realizar la petición Http al servicio de estrenos
     */
    fun getRemoteMovies(type:String): Flowable<ResponseListMovies> {
        return moviesRepository.getRemoteMovies(type)
    }
}