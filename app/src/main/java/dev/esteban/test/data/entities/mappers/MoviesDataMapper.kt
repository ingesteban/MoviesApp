package dev.esteban.test.data.entities.mappers

import dev.esteban.test.data.entities.model.Movies
import dev.esteban.test.data.entities.model.ResponseListMovies

class MoviesDataMapper {

    fun mapToEntity(results: List<Movies>): ResponseListMovies = ResponseListMovies(
        results = results
    )


    fun mapMovieWithType(movie: Movies, type:String): Movies = Movies(
        id = movie.id,
        voteCount = movie.voteCount,
        video = movie.video,
        voteAverage = movie.voteAverage,
        title = movie.title,
        popularity = movie.popularity,
        posterPath = movie.posterPath,
        originalLanguage = movie.originalLanguage,
        originalTitle = movie.originalTitle,
        //genreIds = movie.genreIds,
        backdropPath = movie.backdropPath,
        adult = movie.adult,
        overview = movie.overview,
        releaseDate = movie.releaseDate,
        type = type
    )

}
