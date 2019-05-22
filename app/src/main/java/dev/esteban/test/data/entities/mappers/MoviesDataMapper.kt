package dev.esteban.test.data.entities.mappers

import dev.esteban.test.data.entities.model.Movies
import dev.esteban.test.data.entities.model.ResponseListMovies

class MoviesDataMapper {

    fun mapToEntity(results: List<Movies>): ResponseListMovies = ResponseListMovies(
        results = results
    )

}
