package dev.esteban.test.presentation.movie_videos

import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import dev.esteban.test.R
import dev.esteban.test.data.entities.model.ResponseListMovieVideos
import dev.esteban.test.data.entities.utils.Resource
import dev.esteban.test.data.entities.utils.ResourceState
import dev.esteban.test.presentation.utils.BaseActivity
import kotlinx.android.synthetic.main.activity_movie_videos.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import android.view.MenuItem


class MovieVideosActivity : BaseActivity() {

    private val listmovieVideosVM: MovieVideosViewModel by viewModel()
    private val moviesAdapter: MovieVideosAdapter by inject()
    private var movieId: Int = 0

    companion object{
        private var ID = "ID"
        private var NAME = "NAME"
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_movie_videos
    }

    override fun initializeView() {
        movieId = intent?.extras?.getInt(ID)!!
        val movieName = intent?.extras?.getString(NAME)!!
        initObservers()

        //Se inicializa el recycler
        val linearManager = LinearLayoutManager(this)

        listRvMovieVideos.adapter = moviesAdapter
        listRvMovieVideos.layoutManager = linearManager

        listmovieVideosVM.getMovieVideosById(movieId)
        supportActionBar?.title = getString(R.string.movie_videos_videos_of) +" "+ movieName
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    private fun initObservers() {
        //Observador del request init
        listmovieVideosVM.getListMovieVideosLiveData().observe(this, Observer { render(it) })
    }

    private fun render(it: Resource<ResponseListMovieVideos>?) {
        when (it?.state) {
            ResourceState.LOADING -> listLAMovieVideosLoading.visibility = View.VISIBLE
            ResourceState.SUCCESS -> showData(it)
            ResourceState.ERROR -> showEmptyData()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            android.R.id.home -> {
                finish()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    /**
     * Metodo utilizado para mostrar en pantalla las películas obtenidas
     */
    private fun showData(responseListMovies: Resource<ResponseListMovieVideos>?) {
        responseListMovies?.data?.results?.let {
            listIvMovieVideosEmpty.visibility = View.GONE
            listRvMovieVideos.visibility = View.VISIBLE
            listLAMovieVideosLoading.visibility = View.GONE
            listMovieVideosStringEmpty.visibility = View.GONE

            //Se carga el recycler con las películas
            moviesAdapter.setItemsList(it)
        } ?: showEmptyData()
    }

    /**
     * Metodo utilizado para mostrar el indicador de datos vacios y ocultar el recycler
     */
    private fun showEmptyData() {
        listIvMovieVideosEmpty.visibility = View.VISIBLE
        listMovieVideosStringEmpty.visibility = View.VISIBLE
        listRvMovieVideos.visibility = View.GONE
        listLAMovieVideosLoading.visibility = View.GONE
    }


}
