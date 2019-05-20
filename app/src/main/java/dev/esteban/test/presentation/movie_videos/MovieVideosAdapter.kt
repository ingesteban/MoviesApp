package dev.esteban.test.presentation.movie_videos

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import dev.esteban.test.R
import dev.esteban.test.data.entities.model.Video
import dev.esteban.test.presentation.movie_video.MovieVideoActivity
import kotlinx.android.synthetic.main.item_recycler_movie_videos.view.*
import kotlinx.android.synthetic.main.item_recycler_movies.view.item_container

/**
 * Created by Esteban Barrios on 14/05/2019.
 */
class MovieVideosAdapter : androidx.recyclerview.widget.RecyclerView.Adapter<MovieVideosAdapter.MovieVideosHolder>() {

    private var videos: List<Video> = listOf()
    private var context: Context? = null
    /**
     * Este metodo asocia el layout item_recycler_movies.xml con el recyclerView (infla el layout)
     *
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieVideosHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_movie_videos, parent, false)
        context = parent.context
        return MovieVideosHolder(view)
    }

    /**
     * Cantidad de elementos que contiene la lista de servicios
     */
    override fun getItemCount(): Int {
        return videos.size
    }

    /**
     * Notifica al adapter el cambio en los items a mostrar
     */
    fun setItemsList(moviesList: List<Video>) {
        videos = moviesList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: MovieVideosHolder, position: Int) {
        val video = videos[position]

        holder.movieVideoName.text = video.name
        holder.movieVideoType.text = video.type

        holder.itemContainer.setOnClickListener {
            var intent = Intent(context, MovieVideoActivity::class.java)
            intent.putExtra(KEY, video.key)
            context?.startActivity(intent)
        }
    }

    class MovieVideosHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {
        val movieVideoName: TextView = itemView.movie_video_name
        val movieVideoType: TextView = itemView.movie_video_type
        val itemContainer: CardView = itemView.item_container
    }

    companion object {
        private var KEY: String = "KEY"
    }
}