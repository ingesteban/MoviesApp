package dev.esteban.test.presentation.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.esteban.test.data.entities.model.Movies
import dev.esteban.test.data.entities.utils.Resource
import dev.esteban.test.data.entities.utils.setError
import dev.esteban.test.data.entities.utils.setLoading
import dev.esteban.test.data.entities.utils.setSuccess
import dev.esteban.test.domain.uc.GetMovieUC
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Esteban Barrios on 14/05/2019.
 */
class MovieViewModel(
    private val getMovieUC: GetMovieUC,
    private val back: Scheduler,
    private val front: Scheduler,
    private val movieLiveData: MutableLiveData<Resource<Movies>>
): ViewModel() {

    // Bolsa de disposables
    private var disposeBag: CompositeDisposable = CompositeDisposable()

    /**
     * Retorna la instancia del LiveData que controla el estado de la petición de películas
     */
    fun getMoviesState(): LiveData<Resource<Movies>> {
        return movieLiveData
    }

    /**
     * Genera la petición de peliculas de estreno
     */
    fun getMovie(id:Int){
        disposeBag.add(getMovieUC.getMovieById(id)
            .doOnSubscribe {
                movieLiveData.setLoading()
            }
            .subscribeOn(back)
            .observeOn(front)
            .subscribe({
                movieLiveData.setSuccess(it)
            }, {error: Throwable ->
                movieLiveData.setError(error.message)
            })
        )
    }


    public override fun onCleared() {
        super.onCleared()
        disposeBag.clear()
    }

}
