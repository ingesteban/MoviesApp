package dev.esteban.test.presentation.movies

import android.view.View
import androidx.lifecycle.Observer
import dev.esteban.test.R
import dev.esteban.test.data.entities.utils.Resource
import dev.esteban.test.data.entities.utils.ResourceState
import dev.esteban.test.data.entities.model.ResponseListMovies
import dev.esteban.test.presentation.utils.BaseActivity
import kotlinx.android.synthetic.main.activity_movies.*
import org.koin.android.ext.android.inject
import androidx.recyclerview.widget.GridLayoutManager
import org.koin.android.viewmodel.ext.android.viewModel


/**
 * Created by Esteban Barrios on 14/05/2019.
 */
class MoviesActivity : BaseActivity() {

    private val listMoviesVM: MoviesViewModel by viewModel()
    private val moviesAdapter: MoviesAdapter by inject()

    companion object{
        private var TopRated = "top_rated"
        private var Upcoming = "upcoming"
        private var Popular = "popular"
        private var Local = "Local"
        private var Remote = "Remote"
        private var currentListShowed = TopRated

    }

    override fun getLayoutId(): Int {
        return R.layout.activity_movies
    }

    override fun initializeView() {
        initObservers()

        //Se inicializa el recycler
        val linearManager = GridLayoutManager(this, 2)

        listRvMovies.adapter = moviesAdapter
        listRvMovies.layoutManager = linearManager

        listMoviesVM.getMovies(currentListShowed)
        supportActionBar?.title = getString(R.string.button_top_rated)

        button_top_rated.setOnClickListener {
            currentListShowed = TopRated
            listMoviesVM.getMovies(currentListShowed)
            supportActionBar?.title = getString(R.string.button_top_rated)
        }

        button_upcoming.setOnClickListener {
            currentListShowed = Upcoming
            listMoviesVM.getMovies(currentListShowed)
            supportActionBar?.title = getString(R.string.button_upcoming)
        }

        button_popular.setOnClickListener {
            currentListShowed = Popular
            listMoviesVM.getMovies(currentListShowed)
            supportActionBar?.title = getString(R.string.button_popular)
        }

    }

    private fun initObservers() {
        listMoviesVM.getlistLocalMoviesState().observe(this, Observer { render(it, Local) })
        listMoviesVM.getlistRemoteMoviesState().observe(this, Observer { render(it, Remote) })
    }

    private fun render(it: Resource<ResponseListMovies>?, from:String) {
        when (it?.state) {
            ResourceState.LOADING -> listPbLoading.visibility = View.VISIBLE
            ResourceState.SUCCESS -> showData(it)
            ResourceState.ERROR -> error(from)
        }
    }

    private fun error(from:String){
        if(from == Local){
            showEmptyData()
        } else {
            listMoviesVM.getLocalMovies(currentListShowed)
        }
    }

    /**
     * Metodo utilizado para mostrar en pantalla las películas obtenidas
     */
    private fun showData(responseListMovies: Resource<ResponseListMovies>?) {
        responseListMovies?.data?.results?.let {
            listIvEmpty.visibility = View.GONE
            listRvMovies.visibility = View.VISIBLE
            listPbLoading.visibility = View.GONE

            //Se carga el recycler con las películas
            moviesAdapter.setItemsList(it)
        } ?: showEmptyData()
    }

    /**
     * Metodo utilizado para mostrar el indicador de datos vacios y ocultar el recycler
     */
    private fun showEmptyData() {
        listIvEmpty.visibility = View.VISIBLE
        listRvMovies.visibility = View.GONE
        listPbLoading.visibility = View.GONE
    }


}
