package com.practice.moviedb;

import android.os.Bundle;

import com.practice.moviedb.adapters.MovieListAdapter;
import com.practice.moviedb.databinding.ActivityMainBinding;
import com.practice.moviedb.models.Result;
import com.practice.moviedb.viewmodels.TopRatedMovieViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private TopRatedMovieViewModel viewModel;

    private MovieListAdapter adapter;

    private List<Result> movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setSupportActionBar(binding.toolbar);

        viewModel = ViewModelProviders.of(this).get(TopRatedMovieViewModel.class);
        binding.setTopRatedMovieModel(viewModel);

        initRecyclerView();
    }

    private void initRecyclerView() {

        // Call Loader


        // Initializing with blank list

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        binding.containMain.movieList.setLayoutManager(layoutManager);

        adapter = new MovieListAdapter(this);
        adapter.setMovieList(movieList);
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
