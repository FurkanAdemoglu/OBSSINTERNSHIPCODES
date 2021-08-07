package com.example.apimvvm.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.apimvvm.R
import com.example.apimvvm.model.Show

class TvShowAdapter:RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder>() {
    var list: List<Show>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tv_show, parent, false)
        return TvShowViewHolder(view)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        list?.get(position)?.let { holder.bind(it) }
    }

    override fun getItemCount() = list?.size ?: 0

    class TvShowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imageView = itemView.findViewById<ImageView>(R.id.image)
        val textViewName = itemView.findViewById<TextView>(R.id.name)
        val textViewGenres = itemView.findViewById<TextView>(R.id.genre)

        fun bind(show: Show) {
            Glide.with(itemView.context).load(show.image?.medium).into(imageView)
            textViewName.text = show.name
            var genres = ""
            show.genres?.let {
                for (genre in show.genres) {
                    genres += "$genre, "
                }
            }
            textViewGenres.text = genres
        }

    }

}