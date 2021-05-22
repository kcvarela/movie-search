package com.kcv.moviesearch.presentation

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kcv.moviesearch.R
import com.kcv.moviesearch.domain.SearchResponse

@BindingAdapter("listData")
fun bindRecyclerView(
    recyclerView: RecyclerView,
    data: SearchResponse?
) {
    val adapter = recyclerView.adapter as SearchAdapter


    adapter.submitList(data?.search)

}

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {

    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image)
            )
            .into(imgView)
    }
}

@BindingAdapter("omdApiStatus")
fun bindStatus(
    statusImageView: ImageView,
    status: OmdApiStatus?
) {
    when (status) {
        OmdApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        OmdApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        OmdApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }

    }
}