package br.com.isgreen.darkside.screen.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.isgreen.darkside.R
import br.com.isgreen.darkside.data.model.movie.Movie
import br.com.isgreen.darkside.databinding.ActivityMovieItemBinding
import br.com.isgreen.darkside.extension.loadImage
import br.com.isgreen.darkside.extension.setShapeAppearance
import com.google.android.material.shape.CornerFamily

/**
 * Created by Éverdes Soares on 01/30/2021.
 */

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private val movies = mutableListOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ActivityMovieItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]

        holder.binding.apply {
            tvTitle.text = movie.title
            tvDescription.text = movie.description
            tvReleaseDate.text = movie.releaseDate
            ivPoster.loadImage(movie.image, R.drawable.ic_baseline_image_24)

            val cutCornerSizeSmall = tvReleaseDate.context.resources.getDimension(R.dimen.shape_cut_corner_small)
            tvReleaseDate.setShapeAppearance(
                cornerFamily = CornerFamily.CUT,
                cornerSizeTopRight = 0f,
                cornerSizeBottomLeft = 0f,
                cornerSizeTopLeft = cutCornerSizeSmall,
                cornerSizeBottomRight = cutCornerSizeSmall
            )
        }
    }

    override fun getItemCount(): Int = movies.size

    fun addItems(items: List<Movie>) {
        movies.addAll(items)
        notifyDataSetChanged()
    }

    inner class ViewHolder(
        val binding: ActivityMovieItemBinding
    ) : RecyclerView.ViewHolder(binding.root)
}