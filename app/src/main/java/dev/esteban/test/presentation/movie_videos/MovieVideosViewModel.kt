package dev.esteban.test.presentation.movie_videos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.esteban.test.data.entities.model.ResponseListMovieVideos
import dev.esteban.test.data.entities.utils.Resource
import dev.esteban.test.data.entities.utils.setError
import dev.esteban.test.data.entities.utils.setLoading
import dev.esteban.test.data.entities.utils.setSuccess
import dev.esteban.test.domain.uc.GetMovieVideosUC
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable

class MovieVideosViewModel(
    private val getMovieVideosUC: GetMovieVideosUC,
    private val back: Scheduler,
    private val front: Scheduler,
    private val listMovieVideosLiveData: MutableLiveData<Resource<ResponseListMovieVideos>>
):ViewModel(){

    // Bolsa de disposables
    private var disposeBag: CompositeDisposable = CompositeDisposable()

    /**
     * Retorna la instancia del LiveData que controla el estado de la petición de películas
     */
    fun getListMovieVideosLiveData(): LiveData<Resource<ResponseListMovieVideos>> {
        return listMovieVideosLiveData
    }

    /**
     * Genera la petición de peliculas de estreno
     */
    fun getMovieVideosById(id:Int){
        disposeBag.add(getMovieVideosUC.getMovieVideosById(id)
            .doOnSubscribe {
                listMovieVideosLiveData.setLoading()
            }
            .subscribeOn(back)
            .observeOn(front)
            .subscribe({
                onSuccess(it)
            }, {error: Throwable ->
                listMovieVideosLiveData.setError(error.message)
            })
        )
    }

    private fun onSuccess(response : ResponseListMovieVideos?) = response?.results?.let {
        if(it.isEmpty()) {
            listMovieVideosLiveData.setError("La lista esta vacia")
        } else {
            listMovieVideosLiveData.setSuccess(response)
        }
    }

    public override fun onCleared() {
        super.onCleared()
        disposeBag.clear()
    }
}