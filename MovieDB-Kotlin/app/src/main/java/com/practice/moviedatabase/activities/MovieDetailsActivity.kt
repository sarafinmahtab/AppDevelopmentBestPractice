package com.practice.moviedatabase.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.practice.moviedatabase.R

import kotlinx.android.synthetic.main.activity_movie.*
import kotlinx.android.synthetic.main.content_movie.*

class MovieDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initViews()
    }

    private fun initViews() {
        Glide.with(this)
            .load(intent.getStringExtra("poster_url"))
            .placeholder(R.drawable.ic_movie_poster)
            .into(posterImageView)

        movieTitleTextView.text = intent.getStringExtra("title")
        movieReleasedTextView.text = String.format("Released : %s", intent.getStringExtra("release_date"))
        averageVoteTextView.text = intent.getStringExtra("vote_average")
        overviewTextView.text = intent.getStringExtra("overview")

        if (intent.getBooleanExtra("checked", false)) {
            movieTitleTextView.visibility = View.VISIBLE
            movieReleasedTextView.visibility = View.VISIBLE
            averageVoteTextView.visibility = View.VISIBLE
            overviewTextView.visibility = View.VISIBLE
        } else {
            movieTitleTextView.visibility = View.GONE
            movieReleasedTextView.visibility = View.GONE
            averageVoteTextView.visibility = View.GONE
            overviewTextView.visibility = View.GONE
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
