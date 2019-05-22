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
    private val listLocalMoviesLiveData: MutableLiveData<Resource<ResponseListMovies>>,
    private val listRemoteMoviesLiveData: MutableLiveData<Resource<ResponseListMovies>>
): ViewModel() {

    // Bolsa de disposables
    private var disposeBag: CompositeDisposable = CompositeDisposable()

    /**
     * Retorna la instancia del LiveData que controla el estado de la petición de películas
     */
    fun getlistLocalMoviesState(): LiveData<Resource<ResponseListMovies>> {
        return listLocalMoviesLiveData
    }

    fun getlistRemoteMoviesState(): LiveData<Resource<ResponseListMovies>> {
        return listRemoteMoviesLiveData
    }

    /**
     * Genera la petición de peliculas
     */
    fun getMovies(type:String){
        disposeBag.add(listMoviesUC.getRemoteMovies(type)
            .doOnSubscribe {
                listRemoteMoviesLiveData.setLoading()
            }
            .subscribeOn(back)
            .observeOn(front)
            .subscribe({ response ->
                response.results?.let {
                    if(it.isEmpty()) {
                        listRemoteMoviesLiveData.setError("La lista esta vacia")
                    } else {
                        listRemoteMoviesLiveData.setSuccess(response)
                    }
                }
            }, {error: Throwable ->
                listRemoteMoviesLiveData.setError(error.message)
            })
        )
    }

    fun getLocalMovies(type:String){
        disposeBag.add(listMoviesUC.getLocalMovies(type)
            .doOnSubscribe {
                listLocalMoviesLiveData.setLoading()
            }
            .subscribeOn(back)
            .observeOn(front)
            .subscribe({ response ->
                response.results?.let {
                    if(it.isEmpty()) {
                        listLocalMoviesLiveData.setError("La lista esta vacia")
                    } else {
                        listLocalMoviesLiveData.setSuccess(response)
                    }
                }
            }, {error: Throwable ->
                listLocalMoviesLiveData.setError("La lista esta vacia")
            })
        )
    }


    public override fun onCleared() {
        super.onCleared()
        disposeBag.clear()
    }

}