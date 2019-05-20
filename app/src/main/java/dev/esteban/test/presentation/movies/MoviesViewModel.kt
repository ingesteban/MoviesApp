package dev.esteban.test.presentation.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.esteban.test.data.entities.model.ResponseListMovies
import dev.esteban.test.data.entities.utils.Resource
import dev.esteban.test.data.entities.utils.setError
import dev.esteban.test.data.entities.utils.setLoading
import dev.esteban.test.data.entities.utils.setSuccess
import dev.esteban.test.domain.uc.ListMoviesUC
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Esteban Barrios on 14/05/2019.
 */
class MoviesViewModel(
    private val listMoviesUC: ListMoviesUC,
    private val back: Scheduler,
    private val front: Scheduler,
    private val listMoviesLiveData: MutableLiveData<Resource<ResponseListMovies>>
): ViewModel() {

    // Bolsa de disposables
    private var disposeBag: CompositeDisposable = CompositeDisposable()

    /**
     * Retorna la instancia del LiveData que controla el estado de la petición de películas
     */
    fun getListMoviesState(): LiveData<Resource<ResponseListMovies>> {
        return listMoviesLiveData
    }

    /**
     * Genera la petición de peliculas de estreno
     */
    fun getMovies(type:String){
        disposeBag.add(listMoviesUC.getMovies(type)
            .doOnSubscribe {
                listMoviesLiveData.setLoading()
            }
            .subscribeOn(back)
            .observeOn(front)
            .subscribe({
                onSuccess(it)
            }, {error: Throwable ->
                listMoviesLiveData.setError(error.message)
            })
        )
    }

    private fun onSuccess(response : ResponseListMovies?) = response?.results?.let {
        if(it.isEmpty()) {
            listMoviesLiveData.setError("La lista esta vacia")
        } else {
            listMoviesLiveData.setSuccess(response)
        }
    }

    public override fun onCleared() {
        super.onCleared()
        disposeBag.clear()
    }

}