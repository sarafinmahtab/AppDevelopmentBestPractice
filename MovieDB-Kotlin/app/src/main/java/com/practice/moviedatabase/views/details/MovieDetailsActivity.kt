package com.practice.moviedatabase.views.details

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.practice.moviedatabase.R
import kotlinx.android.synthetic.main.activity_movie_details.*
import kotlinx.android.synthetic.main.content_movie_details.*

class MovieDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initViews()
    }

    private fun initViews() {
        Glide.with(this)
            .load(intent.getStringExtra("backdrop_url"))
            .placeholder(R.drawable.ic_movie_poster)
            .into(backdropPosterImageView)

        Glide.with(this)
            .load(intent.getStringExtra("poster_url"))
            .placeholder(R.drawable.ic_movie_poster)
            .into(posterImageView)

        movieTitleTextView.text = intent.getStringExtra("title")
        movieReleasedTextView.text = String.format("Released : %s", intent.getStringExtra("release_date"))
        averageVoteTextView.text = intent.getStringExtra("vote_average")
        overviewTextView.text = intent.getStringExtra("overview")
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
