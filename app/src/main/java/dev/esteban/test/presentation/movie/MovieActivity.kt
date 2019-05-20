package dev.esteban.test.presentation.movie

import android.content.Intent
import android.view.View
import androidx.lifecycle.Observer
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import dev.esteban.test.R
import dev.esteban.test.data.entities.model.Movies
import dev.esteban.test.data.entities.utils.Resource
import dev.esteban.test.data.entities.utils.ResourceState
import dev.esteban.test.presentation.movie_videos.MovieVideosActivity
import dev.esteban.test.presentation.utils.BaseActivity
import kotlinx.android.synthetic.main.activity_movie.*
import kotlinx.android.synthetic.main.content_scrolling.*
import org.koin.android.viewmodel.ext.android.viewModel


class MovieActivity : BaseActivity() {

    private val movieVM: MovieViewModel by viewModel()

    private var movieId: Int = 0

    override fun getLayoutId(): Int {
        return R.layout.activity_movie
    }

    override fun initializeView() {
        movieId = intent.extras.getInt(ID)
        initObservers()
        movieVM.getMovie(movieId)
    }

    private fun initObservers() {
        //Observador del request init
        movieVM.getMoviesState().observe(this, Observer { render(it) })
    }

    private fun initHandlers(movie_name:String) {
        back_image.setOnClickListener {
            finish()
        }
        check_videos.setOnClickListener {
            var intent = Intent(this, MovieVideosActivity::class.java)
            intent.putExtra(ID, movieId)
            intent.putExtra(NAME, movie_name)

            startActivity(intent)
        }
    }

    private fun render(it: Resource<Movies>?) {
        when (it?.state) {
            ResourceState.LOADING -> movieLoading.visibility = View.VISIBLE
            ResourceState.SUCCESS -> showData(it.data)
            ResourceState.ERROR -> showMessageInformation(getString(R.string.error))
        }
    }

    /**
     * Metodo utilizado para mostrar en pantalla las películas obtenidas
     */
    private fun showData(movieResource: Movies?) {
        movieResource?.let {
            val imageUrl = getString(R.string.base_url_image_the_movie) + it.backdropPath

            Picasso.get().load(imageUrl).into(image_movie, object : Callback {
                override fun onSuccess() {
                    imgMovieLoading.visibility = View.GONE
                }

                override fun onError(e: Exception?) {
                    imgMovieLoading.visibility = View.GONE
                }
            })

            title_movie.text = it.title
            overview_movie.text = it.overview
            release_date.text = it.releaseDate
            is_for_adult.text = if (it.adult) "Yes" else "No"
            initHandlers(it.title)
        }

        movieLoading.visibility = View.GONE
    }


    companion object {
        private var ID: String = "ID"
        private var NAME: String = "NAME"
    }
}
