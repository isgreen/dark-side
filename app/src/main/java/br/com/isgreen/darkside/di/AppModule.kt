package br.com.isgreen.darkside.di

import br.com.isgreen.darkside.dataaccess.local.PreferencesHelper
import br.com.isgreen.darkside.dataaccess.local.PreferencesHelperImpl
import org.koin.dsl.module

/**
 * Created by Éverdes Soares on 01/24/2021.
 */

val appModule = module {
    single<PreferencesHelper> { PreferencesHelperImpl(get()) }
}