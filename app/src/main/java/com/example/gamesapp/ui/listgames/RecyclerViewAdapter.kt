package com.example.gamesapp.ui.listgames

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gamesapp.data.entity.GameResponse
import com.example.gamesapp.databinding.ItemGameBinding
import com.example.gamesapp.utils.show

class RecyclerViewAdapter :
    PagingDataAdapter<GameResponse, RecyclerViewAdapter.ListGameViewHolder>(DiffUtilCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListGameViewHolder {
        val binding = ItemGameBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ListGameViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListGameViewHolder, position: Int) {
        val game = getItem(position)

        holder.binding.apply {
            gameName.text = game?.name

            Glide.with(itemList.context)
                .load(game?.backgroundImage)
                .centerCrop()
                .into(image)

            val platforms = game?.platforms

            if (platforms != null) {
                for (item in platforms) {
                    with(item.platform.name.lowercase()) {
                        when {
                            contains("pc") -> platformWindows.show()
                            contains("playstation") -> platformPlaystation.show()
                            contains("xbox") -> platformXbox.show()
                            contains("amiga") -> platformAmiga.show()
                            contains("android") -> platformAndroid.show()
                            contains("atari") -> platformAtari.show()
                            contains("linux") -> platformLinux.show()
                            contains("mobile") -> platformMobile.show()
                            contains("sega") -> platformSega.show()
                            contains("switch") -> platformSwitch.show()
                        }

                    }
                }
            }

            itemList.setOnClickListener {
                val action = ListGamesFragmentDirections.actionListGamesFragmentToDetailGameFragment(game!!.id)
                itemList.findNavController().navigate(action)
            }

        }
    }


    inner class ListGameViewHolder(val binding: ItemGameBinding) :
        RecyclerView.ViewHolder(binding.root)

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
