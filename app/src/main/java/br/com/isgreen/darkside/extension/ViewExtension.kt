package br.com.isgreen.darkside.extension

import android.graphics.drawable.Drawable
import android.view.View
import androidx.annotation.DimenRes
import androidx.annotation.Dimension
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.view.ViewCompat
import androidx.core.view.setPadding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.ShapeAppearanceModel

/**
 * Created Éverdes Soares on 01/28/2021.
 */

//region View
fun View.setShapeAppearance(
    @CornerFamily cornerFamily: Int,
    @Dimension cornerSizeTopLeft: Float,
    @Dimension cornerSizeTopRight: Float,
    @Dimension cornerSizeBottomLeft: Float,
    @Dimension cornerSizeBottomRight: Float
) {
    val shapeAppearanceModel = ShapeAppearanceModel().toBuilder()
        .setTopLeftCorner(cornerFamily, cornerSizeTopLeft)
        .setTopRightCorner(cornerFamily, cornerSizeTopRight)
        .setBottomLeftCorner(cornerFamily, cornerSizeBottomLeft)
        .setBottomRightCorner(cornerFamily, cornerSizeBottomRight)
        .build()

    ViewCompat.setBackground(this, MaterialShapeDrawable(shapeAppearanceModel))
}
//endregion View

//region ImageView
fun AppCompatImageView?.loadImage(
    urlImage: String?,
    @DrawableRes placeholderError: Int,
    @DimenRes placeholderErrorPadding: Int = 0,
    onLoadingFinished: (success: Boolean) -> Unit = {}
) {
    this?.let {
        val url = urlImage ?: ""

        val requestListener = object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                it.setPadding(placeholderErrorPadding)
                onLoadingFinished(false)
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                it.setPadding(0)
                onLoadingFinished(true)
                return false
            }
        }

        Glide.with(it.context)
            .load(url)
            .apply(
                RequestOptions.circleCropTransform()
                    .centerCrop()
                    .error(placeholderError)
            )
            .listener(requestListener)
            .into(it)
    }
}
//endregion ImageView