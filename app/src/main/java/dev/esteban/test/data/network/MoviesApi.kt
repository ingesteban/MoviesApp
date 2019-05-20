package dev.esteban.test.data.network

import dev.esteban.test.data.entities.model.Movies
import dev.esteban.test.data.entities.model.ResponseListMovieVideos
import dev.esteban.test.data.entities.model.ResponseListMovies
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Esteban Barrios on 14/05/2019.
 */
interface MoviesApi {

    /**
     * Endpoint encargado de realizar la petición Http del servicio de peliculas de estreno
     */
    @GET("movie/upcoming")
    fun getUpcoming(): Flowable<ResponseListMovies>

    /**
     * Endpoint encargado de realizar la petición Http del servicio de peliculas mejor puntuadas
     */
    @GET("movie/top_rated")
    fun getTopRated(): Flowable<ResponseListMovies>

    /**
     * Endpoint encargado de realizar la petición Http del servicio de peliculas mejor puntuadas
     */
    @GET("movie/popular")
    fun getPopular(): Flowable<ResponseListMovies>

    /**
     * Endpoint encargado de realizar la petición Http del servicio de peliculas mejor puntuadas
     */
    @GET("movie/{movieId}")
    fun getMovieById(@Path("movieId") movieId: Int): Flowable<Movies>

    /**
    * Endpoint encargado de realizar la petición Http del servicio de peliculas mejor puntuadas
    */
    @GET("movie/{movieId}/videos")
    fun getMovieVideosById(@Path("movieId") movieId: Int): Flowable<ResponseListMovieVideos>




}