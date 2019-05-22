package dev.esteban.test.data.repository

import dev.esteban.test.data.entities.model.Movies
import dev.esteban.test.data.entities.model.ResponseListMovieVideos
import dev.esteban.test.data.entities.model.ResponseListMovies
import dev.esteban.test.data.network.MoviesApi
import io.reactivex.Flowable


class MoviesRemoteImpl constructor(private val api: MoviesApi) {

    fun getMovies(type: String): Flowable<ResponseListMovies> {
        return when (type) {
            TopRated -> {
                api.getTopRated()
            }
            Popular -> {
                api.getPopular()
            }
            Upcoming -> {
                api.getUpcoming()
            }
            else -> api.getTopRated()
        }
    }

    fun getMovieById(id: Int): Flowable<Movies> {
        return api.getMovieById(id)
    }

    fun getMovieVideosById(id: Int): Flowable<ResponseListMovieVideos> {
        return api.getMovieVideosById(id)
    }

    companion object {
        private var TopRated = "top_rated"
        private var Popular = "popular"
        private var Upcoming = "upcoming"
    }

}