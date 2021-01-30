package br.com.isgreen.darkside.dataaccess.local

/**
 * Created by Éverdes Soares on 01/24/2021.
 */

interface PreferencesHelper {

    companion object {
        const val THEME_MODE = "themeMode"
    }

    suspend fun clearData()

    fun getThemeMode(): Int
    fun saveThemeMode(mode: Int)
}