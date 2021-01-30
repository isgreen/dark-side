package br.com.isgreen.darkside.data.model.movie

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created by Éverdes Soares on 01/24/2021.
 */

@Parcelize
data class Movie(
    val releaseDate: String,
    val title: String,
    val description: String,
    val image: String?
) : Parcelable