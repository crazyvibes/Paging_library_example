package in.crazyvibes.paging_library_example.ui;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import in.crazyvibes.paging_library_example.R;
import in.crazyvibes.paging_library_example.databinding.ActivityMainBinding;
import in.crazyvibes.paging_library_example.ui.adapter.MovieAdapter;
import in.crazyvibes.paging_library_example.ui.model.MainActivityViewModel;
import in.crazyvibes.paging_library_example.ui.model.Movie;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<Movie> movies;
    private MovieAdapter movieAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private MainActivityViewModel mainActivityViewModel;
    private ActivityMainBinding activityMainBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("TMDB Popular Movies Today");
        activityMainBinding= DataBindingUtil.setContentView(this,R.layout.activity_main); //binding data with layout

        mainActivityViewModel= ViewModelProviders.of(this).get(MainActivityViewModel.class);  // create instance of view model using viewModel providers
        getPopularMovies();

        swipeRefreshLayout = activityMainBinding.swipeLayout;
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                getPopularMovies();

            }
        });

    }

    public void getPopularMovies() {

        mainActivityViewModel.getAllMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(@Nullable List<Movie> moviesFromLiveData) {
                movies = (ArrayList<Movie>) moviesFromLiveData;
                showOnRecyclerView();
            }
        });


    }

    private void showOnRecyclerView() {

        recyclerView = activityMainBinding.rvMovies;
        movieAdapter = new MovieAdapter(this, movies);

        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {

            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        } else {


            recyclerView.setLayoutManager(new GridLayoutManager(this, 4));


        }

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(movieAdapter);
        movieAdapter.notifyDataSetChanged();

    }

}