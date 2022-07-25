package com.example.myapplication.view.fragment.beranda

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.myapplication.R
import com.example.myapplication.databinding.ItemPosterBinding
import com.example.myapplication.model.ResultsItem
import com.example.myapplication.utils.Constants.BASE_URL_POSTER
import okhttp3.internal.notify

class BerandaAdapter(
    private var listData: MutableList<ResultsItem?>?
) : RecyclerView.Adapter<BerandaAdapter.ViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun setData(listData: MutableList<ResultsItem?>?) {
        this.listData = listData
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemPosterBinding.bind(view)

        fun bind(data: ResultsItem?) {
            with(binding) {
                imgPoster.load(BASE_URL_POSTER + data?.posterPath) {
                    crossfade(true)
                    crossfade(1000)
                    placeholder(android.R.color.darker_gray)
                    error(R.drawable.ic_launcher_foreground)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_poster, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listData?.get(position))
    }

    override fun getItemCount(): Int = listData!!.size
}