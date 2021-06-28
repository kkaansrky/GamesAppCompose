package com.example.gamesapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RecyclerViewAdapter() : PagingDataAdapter<GameResponse,RecyclerViewAdapter.MyViewHolder> (DiffUtilCallBack()) {

    override fun onBindViewHolder(holder: RecyclerViewAdapter.MyViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewAdapter.MyViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.recycler_row, parent, false)

        return MyViewHolder(inflater)
    }

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val imageView: ImageView = view.findViewById(R.id.imageView)
        val tvName: TextView = view.findViewById(R.id.tvName)
        val tvDesc: TextView = view.findViewById(R.id.tvDesc)

        fun bind(data: GameResponse) {
            tvName.text = data.name
            tvDesc.text = data.updated

            Glide.with(imageView)
                .load(data.backgroundImage)
                .circleCrop()
                .into(imageView)

        }
    }

    class DiffUtilCallBack: DiffUtil.ItemCallback<GameResponse>() {
        override fun areItemsTheSame(oldItem: GameResponse, newItem: GameResponse): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: GameResponse, newItem: GameResponse): Boolean {
            return oldItem.id == newItem.id
                    && oldItem.name == newItem.name
        }

    }



}
