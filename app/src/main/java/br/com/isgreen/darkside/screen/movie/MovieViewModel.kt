package br.com.isgreen.darkside.screen.movie

import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.BuildCompat.isAtLeastQ
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.isgreen.darkside.data.model.movie.Movie
import kotlinx.coroutines.launch

/**
 * Created by Éverdes Soares on 01/24/2021.
 */

class MovieViewModel(
    private val repository: MovieContract.Repository
) : ViewModel(), MovieContract.ViewModel {

    override val themeFetched: LiveData<Int>
        get() = mThemeFetched
    override val movies: LiveData<List<Movie>>
        get() = mMovies

    private val mThemeFetched = MutableLiveData<Int>()
    private val mMovies = MutableLiveData<List<Movie>>()

    override fun getMovies() {
        viewModelScope.launch {
            try {
                val movies = repository.getMovies()
                mMovies.postValue(movies)
            } catch (exception: Exception) {
                exception.printStackTrace()
            }
        }
    }

    override fun getCurrentTheme() {
        viewModelScope.launch {
            var theme = repository.getCurrentTheme()

            if (theme == 0) {
                theme = if (isAtLeastQ())
                    AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
                else
                    AppCompatDelegate.MODE_NIGHT_NO
            }

            mThemeFetched.postValue(theme)
        }
    }

    override fun changeTheme(theme: Int) {
        viewModelScope.launch {
            repository.saveTheme(theme)
            mThemeFetched.postValue(theme)
        }
    }
}