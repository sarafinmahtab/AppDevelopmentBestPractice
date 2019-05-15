package com.practice.moviedb.activities;

import android.os.Bundle;

import com.practice.moviedb.R;
import com.practice.moviedb.adapters.MovieListAdapter;
import com.practice.moviedb.databinding.ActivityMainBinding;
import com.practice.moviedb.models.TopRatedMovie;
import com.practice.moviedb.networks.ApiClient;
import com.practice.moviedb.networks.ApiService;
import com.practice.moviedb.repositories.TopRatedMovieRepository;
import com.practice.moviedb.viewmodels.TopRatedMovieViewModel;
import com.practice.moviedb.viewmodels.factories.TopRatedMovieVMFactory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private TopRatedMovieViewModel viewModel;

    private MovieListAdapter adapter;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setSupportActionBar(binding.toolbar);

        progressBar = findViewById(R.id.progress_bar);

        initViewModel();
        initRecyclerView();
        dataObserver();
    }

    private void initViewModel() {

        progressBar.setVisibility(View.VISIBLE);

        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        TopRatedMovieRepository repository = new TopRatedMovieRepository(apiService);

        viewModel = ViewModelProviders.of(this, new TopRatedMovieVMFactory(repository))
                .get(TopRatedMovieViewModel.class);

        viewModel.initTopRatedMovieFromRepo(
                "6371db70ffc8e719f981e307e397452e",
                "en-US",
                "1",
                "vote_average.asc");

        binding.setTopRatedMovieModel(viewModel);
    }

    private void dataObserver() {
        viewModel.getTopRatedMovieLiveData().observe(this, new Observer<TopRatedMovie>() {
            @Override
            public void onChanged(TopRatedMovie topRatedMovie) {

                progressBar.setVisibility(View.GONE);

                updateRecyclerView(topRatedMovie);
            }
        });
    }

    private void updateRecyclerView(TopRatedMovie topRatedMovie) {
        if (topRatedMovie != null) {
            adapter.setTopRatedMovie(topRatedMovie);
            adapter.notifyDataSetChanged();
        }
    }

    private void initRecyclerView() {

        // Initializing with blank list

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        binding.containMain.movieList.setLayoutManager(layoutManager);

        adapter = new MovieListAdapter(this);
        adapter.setTopRatedMovie(new TopRatedMovie());
        binding.containMain.movieList.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
