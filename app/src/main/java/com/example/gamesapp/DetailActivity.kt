package com.example.gamesapp


import android.content.ContentValues.TAG
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide


class DetailActivity : AppCompatActivity() {

    private val viewModel: SharedViewModel by lazy {
        ViewModelProvider(this).get(SharedViewModel::class.java)
    }

    private lateinit var descriptionCardView: CardView
    private lateinit var headerImageView: ImageView
    private lateinit var nameTextView: TextView
    private lateinit var descriptionTextView: TextView
    private lateinit var releaseTextView: TextView
    private lateinit var genresTextView: TextView
    private lateinit var playtimeTextView: TextView
    private lateinit var publishersTextView: TextView


    private lateinit var platformPc: ImageView
    private lateinit var platformPs: ImageView
    private lateinit var platformXbox: ImageView
    private lateinit var platformAmiga: ImageView
    private lateinit var platformAndroid: ImageView
    private lateinit var platformAtari: ImageView
    private lateinit var platformLinux: ImageView
    private lateinit var platformMobile: ImageView
    private lateinit var platformSega: ImageView
    private lateinit var platformSwitch: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.hide()


        initView()
        val ss: String = intent.getStringExtra("id").toString()


        val id: Int = ss.toInt()
        getGameResponse(id)


    }

    private fun initView() {
        descriptionCardView = findViewById(R.id.description)

        nameTextView = findViewById<AppCompatTextView>(R.id.nameTextView)
        headerImageView = findViewById<AppCompatImageView>(R.id.headerImageView)
        descriptionTextView = findViewById<AppCompatTextView>(R.id.descriptionText)
        releaseTextView = findViewById<AppCompatTextView>(R.id.relaeseTextView)
        genresTextView = findViewById<AppCompatTextView>(R.id.genresTextView)
        playtimeTextView = findViewById<AppCompatTextView>(R.id.playtimeTextView)
        publishersTextView = findViewById<AppCompatTextView>(R.id.publishersTextView)

        platformPc = findViewById(R.id.platformWindows)
        platformPs = findViewById(R.id.platformPlaystation)
        platformXbox = findViewById(R.id.platformXbox)
        platformAmiga = findViewById(R.id.platformAmiga)
        platformAndroid = findViewById(R.id.platformAndroid)
        platformAtari = findViewById(R.id.platformAtari)
        platformLinux = findViewById(R.id.platformLinux)
        platformMobile = findViewById(R.id.platformMobile)
        platformSega = findViewById(R.id.platformSega)
        platformSwitch = findViewById(R.id.platformSwitch)
    }

    private fun getGameResponse(gameId: Int) {
        viewModel.refreshGame(gameId, getApiKey())
        viewModel.gameByIdLiveData.observe(this) { response: GameResponse ->
            setGameView(response)
        }
    }

    private fun setGameView(response: GameResponse) {


        nameTextView.text = response.name
        releaseTextView.text = response.released
        playtimeTextView.text = response.playtime.toString()

        setDescriptions(response.description)

        val publishers = response.publishers.map { it.name }
        setPublishers(publishers)

        val genres = response.genres.map { it.name }
        setGenres(genres)




        Glide.with(headerImageView)
            .load(response.backgroundImage)
            .centerCrop()
            .into(headerImageView)


        val platforms = response.platforms

        setPlatforms(platforms)
    }

    private fun setDescriptions(descriptions: String) {
        if (descriptions.isEmpty()) {
            descriptionCardView.visibility = View.GONE
        } else {
            descriptionTextView.text = Html.fromHtml(descriptions)
        }

    }

    private fun setPublishers(publishers: List<String>) {
        if (publishers.isEmpty()) {
            publishersTextView.text = "Unknown"
        } else {
            val publishersTexts = publishers.joinToString(
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

    private fun setGenres(genres: List<String>) {

        if (genres.isEmpty()) {
            genresTextView.text = "Unknown"
        } else {
            val genresTexts = genres.joinToString(
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


    private fun setPlatforms(platforms: List<Platforms>) {
        for (item in platforms) {
            with(item.platform.name) {
                Log.d(TAG, "setPlatforms: " + item.platform.name)
                when {
                    lowercase().contains("pc") -> platformPc.visibility = View.VISIBLE
                    lowercase().contains("playstation") -> platformPs.visibility =
                        View.VISIBLE
                    lowercase().contains("xbox") -> platformXbox.visibility =
                        View.VISIBLE
                    lowercase().contains("amiga") -> platformAmiga.visibility =
                        View.VISIBLE
                    lowercase().contains("android") -> platformAndroid.visibility =
                        View.VISIBLE
                    lowercase().contains("atari") -> platformAtari.visibility =
                        View.VISIBLE
                    lowercase().contains("linux") -> platformLinux.visibility =
                        View.VISIBLE
                    lowercase().contains("mobile") -> platformMobile.visibility =
                        View.VISIBLE
                    lowercase().contains("sega") -> platformSega.visibility =
                        View.VISIBLE
                    lowercase().contains("switch") -> platformSwitch.visibility =
                        View.VISIBLE
                }
            }
        }
    }


    private fun getApiKey(): String {
        val ai: ApplicationInfo = applicationContext.packageManager
            .getApplicationInfo(applicationContext.packageName, PackageManager.GET_META_DATA)

        return ai.metaData["API_KEY"].toString()

    }
}