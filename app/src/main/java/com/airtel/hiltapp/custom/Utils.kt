package com.airtel.hiltapp.custom

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.appcompat.widget.AppCompatImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.airtel.hiltapp.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by SURYA N on 4/6/20.
 */

fun AppCompatImageView.loadImage(url: String, drawable: Drawable) {
    val option = RequestOptions()
        .override(50, 50)
        .placeholder(drawable)
        .error(R.drawable.ic_launcher_background)
    Glide.with(this.context)
        .setDefaultRequestOptions(option)
        .load(url)
        .into(this)
}

fun progressDrawable(context: Context) = CircularProgressDrawable(context).apply {
    strokeWidth = 12f
    centerRadius = 30f
    start()
}

fun <T> Single<T>.loadSub(): Single<T> = this.subscribeOn(Schedulers.newThread())
    .observeOn(
        AndroidSchedulers.mainThread()
    )