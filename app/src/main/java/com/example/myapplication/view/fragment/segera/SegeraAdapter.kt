package com.example.myapplication.view.fragment.segera

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.myapplication.R
import com.example.myapplication.databinding.ItemSegeraBinding
import com.example.myapplication.model.ResultsItem
import com.example.myapplication.utils.Constants.BASE_URL_POSTER

class SegeraAdapter(
    private var listData: MutableList<ResultsItem?>?,
    private var onItemClickCallback: OnItemClickCallback
) : RecyclerView.Adapter<SegeraAdapter.ViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun setData(listData: MutableList<ResultsItem?>?) {
        this.listData = listData
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemSegeraBinding.bind(view)

        fun bind(data: ResultsItem?) {
            with(binding) {
                imgPoster.load(BASE_URL_POSTER + data?.backdropPath)
                txtJudulPertama.text = data?.title
                txtJudul.text = data?.title
                txtDesc.text = data?.overview
                txtTontonSekarang.setOnClickListener {
                    onItemClickCallback.onItemClick(data)
                }
            }
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_segera, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listData?.get(position))
    }

    override fun getItemCount(): Int = listData!!.size

    interface OnItemClickCallback {
        fun onItemClick(data: ResultsItem?)
    }
}