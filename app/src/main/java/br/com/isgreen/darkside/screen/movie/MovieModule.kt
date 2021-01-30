package br.com.isgreen.darkside.screen.movie

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Éverdes Soares on 01/24/2021.
 */

val movieModule = module {
    viewModel { MovieViewModel(get()) }
    factory<MovieContract.Repository> { MovieRepository(get()) }
}