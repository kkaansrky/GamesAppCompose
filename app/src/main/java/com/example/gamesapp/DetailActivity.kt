package com.example.gamesapp

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {

    private val viewModel: SharedViewModel by lazy {
        ViewModelProvider(this).get(SharedViewModel::class.java)
    }

    lateinit var nameTextView:TextView
    lateinit var aliveTextView:TextView
    lateinit var originTextView:TextView
    lateinit var speciesTextView:TextView
    lateinit var headerImageView:ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)





    }

    private fun initView(){
        nameTextView = findViewById<AppCompatTextView>(R.id.nameTextView)
        headerImageView = findViewById<AppCompatImageView>(R.id.headerImageView)
        aliveTextView = findViewById<AppCompatTextView>(R.id.aliveTextView)
        originTextView = findViewById<AppCompatTextView>(R.id.originTextView)
        speciesTextView = findViewById<AppCompatTextView>(R.id.speciesTextView)
    }

    private fun setGameView(gameId:Int){
        viewModel.refreshGame(gameId, getApiKey())
        viewModel.gameByIdLiveData.observe(this) { response ->
            if (response == null) {
                Toast.makeText(
                    this,
                    "Unsuccessful network call!",
                    Toast.LENGTH_SHORT
                ).show()
                return@observe
            }
            val game = response

            nameTextView.text = game.name
            aliveTextView.text = game.metacritic.toString()
            speciesTextView.text = game.released
            originTextView.text = game.updated

            Glide.with(headerImageView)
                .load(game.backgroundImage)
                .circleCrop()
                .into(headerImageView)



        }
    }


    fun getApiKey(): String {
        val ai: ApplicationInfo = applicationContext.packageManager
            .getApplicationInfo(applicationContext.packageName, PackageManager.GET_META_DATA)
        val apiKey = ai.metaData["API_KEY"].toString()

        return apiKey

    }
}