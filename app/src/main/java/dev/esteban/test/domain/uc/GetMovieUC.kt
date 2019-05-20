package dev.esteban.test.domain.uc

import dev.esteban.test.data.entities.model.Movies
import dev.esteban.test.domain.repositories.MoviesRepository
import io.reactivex.Flowable

/**
 * Created by Esteban Barrios on 14/05/2019.
 */
class GetMovieUC(
    private val moviesRepository: MoviesRepository
) {

    /**
     * Retorna el Observable encargado de realizar la petición Http al servicio de estrenos
     */
    fun getMovieById(id:Int): Flowable<Movies> {
        return moviesRepository.getMovieById(id)
    }
}