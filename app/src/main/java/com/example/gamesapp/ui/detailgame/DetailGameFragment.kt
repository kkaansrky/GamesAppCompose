package com.example.gamesapp.ui.detailgame

import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.gamesapp.data.entity.GameResponse
import com.example.gamesapp.data.entity.game.Genres
import com.example.gamesapp.data.entity.game.Platforms
import com.example.gamesapp.data.entity.game.Publishers
import com.example.gamesapp.databinding.FragmentDetailGameBinding
import com.example.gamesapp.utils.Resource
import com.example.gamesapp.utils.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailGameFragment : Fragment() {
    private var _binding: FragmentDetailGameBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DetailGameViewModel by viewModels()
    private val args: DetailGameFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        getGame(args.gameId)
    }

    private fun getGame(gameId: Int) {
        viewModel.getGame(gameId).observe(viewLifecycleOwner , {
            when(it.status){
                Resource.Status.LOADING -> {
                    //binding.progressBar.show()
                    Log.d("TAG", "getGame: "+ it.status)
                }
                Resource.Status.SUCCESS -> {
                    //binding.progressBar.gone()
                    Log.d("TAG", "getGame: "+ it.status)
                    it.data?.let { it -> updateView(it) }
                }
                Resource.Status.ERROR -> {
                    //binding.progressBar.gone()
                    Log.d("TAG", "getGame: "+ it.status)
                    Toast.makeText(activity, "Error", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun updateView(game: GameResponse) {
        binding.apply {
            nameTextView.text = game.name
            relaeseTextView.text = game.released
            playtimeTextView.text = game.playtime.toString()
            descriptionTextLabelTextView.text = Html.fromHtml(game.description)

            Glide.with(headerImageView)
                .load(game.backgroundImage)
                .centerCrop()
                .into(headerImageView)

            mapAndSetPublishers(game.publishers)
            mapAndSetGenres(game.genres)
            setPlatforms(game.platforms)
        }
    }

    private fun mapAndSetGenres(genres: List<Genres>) {
        val genresNameMapped = genres.map { it.name }
        binding.apply {
            if (genresNameMapped.isEmpty()) {
                genresTextView.text = "Unknown"
            } else {
                val genresTexts = genresNameMapped.joinToString(
                    ", ",
                    "",
                    "",
                    -1,
                    "...",
                    null
                )
                genresTextView.text = genresTexts
            }
        }
    }

    private fun mapAndSetPublishers(publishers: List<Publishers>) {
        val publishersNameMapped = publishers.map { it.name }
        binding.apply {
            if (publishersNameMapped.isEmpty()) {
                publishersTextView.text = "Unknown"
            } else {
                val publishersTexts = publishersNameMapped.joinToString(
                    ", ",
                    "",
                    "",
                    -1,
                    "...",
                    null
                )
                publishersTextView.text = publishersTexts
            }
        }
    }

    private fun setPlatforms(platforms: List<Platforms>) {
        binding.apply {
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
    }

}
