package dev.esteban.test.domain.repositories

import dev.esteban.test.data.entities.model.Movies
import dev.esteban.test.data.entities.model.ResponseListMovieVideos
import dev.esteban.test.data.entities.model.ResponseListMovies
import io.reactivex.Flowable

interface MoviesRepository {

    fun getLocalMovies(type:String = ""): Flowable<ResponseListMovies>
    fun getRemoteMovies(type:String = ""): Flowable<ResponseListMovies>
    fun getMovieById(id:Int): Flowable<Movies>
    fun getMovieVideosById(id:Int): Flowable<ResponseListMovieVideos>

}