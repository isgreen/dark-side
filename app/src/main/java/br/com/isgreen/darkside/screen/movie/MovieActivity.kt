package br.com.isgreen.darkside.screen.movie

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import br.com.isgreen.darkside.R
import br.com.isgreen.darkside.databinding.ActivityMovieBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by Éverdes Soares on 01/25/2021.
 */

class MovieActivity : AppCompatActivity() {

    lateinit var binding: ActivityMovieBinding

    private val viewModel: MovieViewModel by viewModel()
    private val movieAdapter: MovieAdapter by lazy { MovieAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configViews()
        initObservers()
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        getData()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_movie, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        menu?.findItem(R.id.menu_item_light)?.isChecked =
            viewModel.themeFetched.value == AppCompatDelegate.MODE_NIGHT_NO
        menu?.findItem(R.id.menu_item_dark)?.isChecked =
            viewModel.themeFetched.value == AppCompatDelegate.MODE_NIGHT_YES
        menu?.findItem(R.id.menu_item_follow_system)?.isChecked =
            viewModel.themeFetched.value == AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_item_light -> viewModel.changeTheme(AppCompatDelegate.MODE_NIGHT_NO)
            R.id.menu_item_dark -> viewModel.changeTheme(AppCompatDelegate.MODE_NIGHT_YES)
            R.id.menu_item_follow_system -> viewModel.changeTheme(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        }
        return true
    }

    private fun configViews() {
        setSupportActionBar(binding.toolbar)
        binding.rvMovie.adapter = movieAdapter
    }

    private fun initObservers() {
        viewModel.movies.observe(this, { movies ->
            movieAdapter.addItems(movies)
        })
        viewModel.themeFetched.observe(this, { theme ->
            AppCompatDelegate.setDefaultNightMode(theme)
        })
    }

    private fun getData() {
        viewModel.getCurrentTheme()
        viewModel.getMovies()
    }
}