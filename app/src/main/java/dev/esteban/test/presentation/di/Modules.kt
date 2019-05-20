package dev.esteban.test.presentation.di

import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import dev.esteban.test.data.db.MoviesDatabase
import dev.esteban.test.data.entities.mappers.MoviesDataMapper
import dev.esteban.test.data.network.MoviesApi
import dev.esteban.test.data.repository.MoviesCacheImpl
import dev.esteban.test.data.repository.MoviesRemoteImpl
import dev.esteban.test.data.repository.MoviesRepositoryImpl
import dev.esteban.test.domain.repositories.MoviesRepository
import dev.esteban.test.domain.uc.GetMovieUC
import dev.esteban.test.domain.uc.GetMovieVideosUC
import dev.esteban.test.domain.uc.ListMoviesUC
import dev.esteban.test.presentation.movie.MovieViewModel
import dev.esteban.test.presentation.movie_videos.MovieVideosAdapter
import dev.esteban.test.presentation.movie_videos.MovieVideosViewModel
import dev.esteban.test.presentation.movies.MoviesAdapter
import dev.esteban.test.presentation.movies.MoviesViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

/**
 * Created by Esteban Barrios on 14/05/2019.
 */

/**
 * Modulo encargado de instanciar los viewModel
 */
val viewModelModule: Module = module {
    viewModel {
        MoviesViewModel(
            listMoviesUC = get(LIST_MOVIES_UC),
            back = Schedulers.io(),
            front = AndroidSchedulers.mainThread(),
            listMoviesLiveData = MutableLiveData()
        )
    }
    viewModel {
        MovieViewModel(
            getMovieUC = get(GET_MOVIES_UC),
            back = Schedulers.io(),
            front = AndroidSchedulers.mainThread(),
            movieLiveData = MutableLiveData()
        )
    }
    viewModel {
        MovieVideosViewModel(
            getMovieVideosUC = get(GET_MOVIE_VIDEOS_UC),
            back = Schedulers.io(),
            front = AndroidSchedulers.mainThread(),
            listMovieVideosLiveData = MutableLiveData()
        )
    }


}

/**
 * Modulo encargado de instanciar los casos de uso
 */
val useCasesModule: Module = module {
    factory(LIST_MOVIES_UC) {
        ListMoviesUC(moviesRepository = get(MOVIES_REPOSITORY_IMPL))
    }
    factory(GET_MOVIES_UC) {
        GetMovieUC(moviesRepository = get(MOVIES_REPOSITORY_IMPL))
    }
    factory(GET_MOVIE_VIDEOS_UC) {
        GetMovieVideosUC(moviesRepository = get(MOVIES_REPOSITORY_IMPL))
    }


}

/**
 * Modulo encargado de instanciar los repositorios
 */
val repositoriesModule: Module = module {
    factory(MOVIES_REPOSITORY_IMPL) {
        MoviesRepositoryImpl(
            remote = get(MOVIES_REMOTE_IMPL),
            cache = get(MOVIES_CACHE_IMPL),
            moviesDataMapper = get(MOVIES_DATA_MAPPER)
        ) as MoviesRepository
    }

    factory(MOVIES_REMOTE_IMPL) { MoviesRemoteImpl(LIST_MOVIES_DATABASE) }
    factory(MOVIES_CACHE_IMPL) {
        MoviesCacheImpl(
            database = get(DATABASE),
            moviesDataMapper = get(MOVIES_DATA_MAPPER)
        )
    }
}

/**
 * Modulo encargado de instanciar los mappers
 */
val mappersModule: Module = module {
    factory(MOVIES_DATA_MAPPER) { MoviesDataMapper() }
}

/**
 * Modulo encargado de instanciar las utilidades
 */
val utilsModule: Module = module {
    factory { MoviesAdapter() }
    factory { MovieVideosAdapter() }
    single(DATABASE) {
        Room.databaseBuilder(androidApplication(), MoviesDatabase::class.java, "movies_db").build()
    }
}

private val retrofit: Retrofit = ManagerNetwork(TestApp.applicationContext().applicationContext).getTheMovieRetrofit()

// Inicializa la instancia que contiene el Endpoint para llamar al servicio que obtiene los listados de peliculas
private val LIST_MOVIES_DATABASE: MoviesApi = retrofit.create(MoviesApi::class.java)

// UseCases Module
private val LIST_MOVIES_UC = named("LIST_MOVIES_UC")
private val GET_MOVIES_UC = named("GET_MOVIES_UC")
private val GET_MOVIE_VIDEOS_UC = named("GET_MOVIE_VIDEOS_UC")

// Repositories Module
private val MOVIES_REPOSITORY_IMPL = named("MOVIES_REPOSITORY_IMPL")
private val MOVIES_REMOTE_IMPL = named("MOVIES_REMOTE_IMPL")
private val MOVIES_CACHE_IMPL = named("MOVIES_CACHE_IMPL")

// Mappers Module
private val MOVIES_DATA_MAPPER = named("MOVIES_DATA_MAPPER")

// Utils Module
private val DATABASE = named("DATABASE")
