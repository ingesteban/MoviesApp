package dev.esteban.test.data.repository

import dev.esteban.test.data.entities.mappers.MoviesDataMapper
import dev.esteban.test.data.entities.model.Movies
import dev.esteban.test.data.entities.model.ResponseListMovieVideos
import dev.esteban.test.data.entities.model.ResponseListMovies
import dev.esteban.test.domain.repositories.MoviesRepository
import io.reactivex.Flowable


class MoviesRepositoryImpl(
    private val remote: MoviesRemoteImpl,
    private val cache: MoviesCacheImpl,
    private val moviesDataMapper: MoviesDataMapper
) : MoviesRepository {

    override fun getRemoteMovies(type: String): Flowable<ResponseListMovies> {
        return remote.getMovies(type).doOnNext { remoteMovies ->
            remoteMovies.results.map {
                moviesDataMapper.mapMovieWithType(it, type)
            }
            cache.saveMovies(remoteMovies)
        }
    }

    override fun getLocalMovies(type: String): Flowable<ResponseListMovies> {
        return cache.getMovies(type)
    }

    override fun getMovies(type: String): Flowable<ResponseListMovies> {
        return Flowable.concat(getRemoteMovies(type), getLocalMovies(type))
    }

    override fun getMovieById(id: Int): Flowable<Movies> {
        return remote.getMovieById(id)
    }

    override fun getMovieVideosById(id: Int): Flowable<ResponseListMovieVideos> {
        return remote.getMovieVideosById(id)
    }
}