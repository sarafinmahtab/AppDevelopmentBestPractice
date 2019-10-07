package com.practice.moviedatabase.views.details

import android.os.Bundle
import com.bumptech.glide.Glide
import com.practice.moviedatabase.R
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_movie_short_details.*
import kotlinx.android.synthetic.main.content_movie_short_details.*

class MovieShortDetailsActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_short_details)
        setupToolbar()

        initViews()
    }

    private fun initViews() {
        Glide.with(this)
            .load(intent.getStringExtra("poster_url"))
            .placeholder(R.drawable.ic_movie_poster)
            .into(posterImageView)

        movieTitleTextView.text = intent.getStringExtra("title")
        movieReleasedTextView.text =
            String.format("Released : %s", intent.getStringExtra("release_date"))
        averageVoteTextView.text = intent.getStringExtra("vote_average")
        overviewTextView.text = intent.getStringExtra("overview")
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = ""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        toolbarTitleTextView.text = intent.getStringExtra("title")
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
