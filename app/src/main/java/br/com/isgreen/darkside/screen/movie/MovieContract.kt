package br.com.isgreen.darkside.screen.movie

import androidx.lifecycle.LiveData
import br.com.isgreen.darkside.data.model.movie.Movie

/**
 * Created by Éverdes Soares on 01/24/2021.
 */

interface MovieContract {

    interface ViewModel {
        val themeFetched: LiveData<Int>
        val movies: LiveData<List<Movie>>

        fun getMovies()
        fun getCurrentTheme()
        fun changeTheme(theme: Int)
    }

    interface Repository {
        suspend fun saveTheme(theme: Int)
        suspend fun getCurrentTheme(): Int
        suspend fun getMovies(): List<Movie>
    }
}