package dev.esteban.test.presentation.movies

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import dev.esteban.test.R
import dev.esteban.test.data.entities.model.Movies
import dev.esteban.test.presentation.movie.MovieActivity
import kotlinx.android.synthetic.main.item_recycler_movies.view.*

/**
 * Created by Esteban Barrios on 14/05/2019.
 */
class MoviesAdapter: androidx.recyclerview.widget.RecyclerView.Adapter<MoviesAdapter.MoviesHolder>() {

    private var movies: List<Movies> = listOf()
    private var context:Context? = null
    /**
     * Este metodo asocia el layout item_recycler_movies.xml con el recyclerView (infla el layout)
     *
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_movies, parent, false)
        context = parent.context
        return MoviesHolder(view)
    }

    /**
     * Cantidad de elementos que contiene la lista de servicios
     */
    override fun getItemCount(): Int {
        return movies.size
    }

    /**
     * Notifica al adapter el cambio en los items a mostrar
     */
    fun setItemsList(moviesList: List<Movies>) {
        movies = moviesList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: MoviesHolder, position: Int) {
        val movie = movies[position]

        holder.movieTitle.text = movie.originalTitle
        val imageUrl = context?.getString(R.string.base_url_image_the_movie)+movie.posterPath
        Picasso.get().load(imageUrl).into(holder.movieImage, object : Callback {
            override fun onSuccess() {
                holder.itemLoading.visibility = View.GONE
            }
            override fun onError(e: Exception?) {
                holder.itemLoading.visibility = View.GONE
            }
        })
        holder.itemContainer.setOnClickListener {
            var intent = Intent(context, MovieActivity::class.java)
            intent.putExtra(ID, movie.id)
            context?.startActivity(intent)
        }
    }

    class MoviesHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {
        val movieTitle: TextView = itemView.movieTitle
        val movieImage: ImageView = itemView.movieImage
        val itemLoading: ImageView = itemView.itemLoading
        val itemContainer: CardView = itemView.item_container
    }

    companion object{
        private var ID: String  = "ID"
    }
}