package com.example.gamesapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RecyclerViewAdapter :
    PagingDataAdapter<GameResponse, RecyclerViewAdapter.MyViewHolder>(DiffUtilCallBack()) {

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(getItem(position)!!)

        holder.itemView.setOnClickListener {

            val context = holder.imageView.context
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("id", holder.gameId.text)
            context.startActivity(intent)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_row, parent, false)

        return MyViewHolder(inflater)
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {


        val imageView: ImageView = view.findViewById(R.id.image)
        private val gameName: TextView = view.findViewById(R.id.gameName)
        val gameId: TextView = view.findViewById(R.id.gameID)
        private val platformPc: ImageView = view.findViewById(R.id.platformWindows)
        private val platformPs: ImageView = view.findViewById(R.id.platformPlaystation)
        private val platformXbox: ImageView = view.findViewById(R.id.platformXbox)
        private val platformAmiga: ImageView = view.findViewById(R.id.platformAmiga)
        private val platformAndroid: ImageView = view.findViewById(R.id.platformAndroid)
        private val platformAtari: ImageView = view.findViewById(R.id.platformAtari)
        private val platformLinux: ImageView = view.findViewById(R.id.platformLinux)
        private val platformMobile: ImageView = view.findViewById(R.id.platformMobile)
        private val platformSega: ImageView = view.findViewById(R.id.platformSega)
        private val platformSwitch: ImageView = view.findViewById(R.id.platformSwitch)


        fun bind(data: GameResponse) {
            gameName.text = data.name
            gameId.text = data.id.toString()
            val platforms = data.platforms

            for (item in platforms) {
                with(item.platform.name) {
                    when {
                        lowercase().contains("pc") -> platformPc.visibility = View.VISIBLE
                        lowercase().contains("playstation") -> platformPs.visibility = View.VISIBLE
                        lowercase().contains("xbox") -> platformXbox.visibility = View.VISIBLE
                        lowercase().contains("amiga") -> platformAmiga.visibility = View.VISIBLE
                        lowercase().contains("android") -> platformAndroid.visibility = View.VISIBLE
                        lowercase().contains("atari") -> platformAtari.visibility = View.VISIBLE
                        lowercase().contains("linux") -> platformLinux.visibility = View.VISIBLE
                        lowercase().contains("mobile") -> platformMobile.visibility = View.VISIBLE
                        lowercase().contains("sega") -> platformSega.visibility = View.VISIBLE
                        lowercase().contains("switch") -> platformSwitch.visibility = View.VISIBLE
                    }
                }
            }


            Glide.with(itemView.context)
                .load(data.backgroundImage)
                .centerCrop()
                .into(imageView)

        }
    }

    class DiffUtilCallBack : DiffUtil.ItemCallback<GameResponse>() {
        override fun areItemsTheSame(oldItem: GameResponse, newItem: GameResponse): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: GameResponse, newItem: GameResponse): Boolean {
            return oldItem.id == newItem.id
                    && oldItem.name == newItem.name
        }

    }


}
