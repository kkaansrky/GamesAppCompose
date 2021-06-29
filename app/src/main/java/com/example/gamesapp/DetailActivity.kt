package com.example.gamesapp

import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {

    private val viewModel: SharedViewModel by lazy {
        ViewModelProvider(this).get(SharedViewModel::class.java)
    }

    private lateinit var nameTextView: TextView
    private lateinit var aliveTextView: TextView
    private lateinit var originTextView: TextView
    private lateinit var speciesTextView: TextView
    private lateinit var headerImageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        initView()
        val ss: String = intent.getStringExtra("id").toString()


        val id: Int = ss.toInt()
        setGameView(id)


    }

    private fun initView() {
        nameTextView = findViewById<AppCompatTextView>(R.id.nameTextView)
        headerImageView = findViewById<AppCompatImageView>(R.id.headerImageView)
        aliveTextView = findViewById<AppCompatTextView>(R.id.aliveTextView)
        originTextView = findViewById<AppCompatTextView>(R.id.originTextView)
        speciesTextView = findViewById<AppCompatTextView>(R.id.speciesTextView)
    }

    private fun setGameView(gameId: Int) {
        viewModel.refreshGame(gameId, getApiKey())
        viewModel.gameByIdLiveData.observe(this) { response: GameResponse ->

            nameTextView.text = response.name
            aliveTextView.text = response.metacritic.toString()
            speciesTextView.text = response.released
            originTextView.text = response.updated

            Glide.with(headerImageView)
                .load(response.backgroundImage)
                .circleCrop()
                .into(headerImageView)


        }
    }


    private fun getApiKey(): String {
        val ai: ApplicationInfo = applicationContext.packageManager
            .getApplicationInfo(applicationContext.packageName, PackageManager.GET_META_DATA)

        return ai.metaData["API_KEY"].toString()

    }
}