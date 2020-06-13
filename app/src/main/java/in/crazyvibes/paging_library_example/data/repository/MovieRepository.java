package in.crazyvibes.paging_library_example.data.repository;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import in.crazyvibes.paging_library_example.R;
import in.crazyvibes.paging_library_example.data.remote.MovieDBResponse;
import in.crazyvibes.paging_library_example.ui.model.Movie;
import in.crazyvibes.paging_library_example.utils.service.MovieDataService;
import in.crazyvibes.paging_library_example.utils.service.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// In MVVM activity should receive data from repository by using view model.
public class MovieRepository {
    private ArrayList<Movie> movies=new ArrayList<>();
    private MutableLiveData<List<Movie>> mutableLiveData =new MutableLiveData<>();
    private Application application;

    public MovieRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData<List<Movie>> getMutableLiveData() {
        MovieDataService movieDataService = RetrofitInstance.getService();

        Call<MovieDBResponse> call = movieDataService.getPopularMovies(application.getApplicationContext().getString(R.string.api_key));

        call.enqueue(new Callback<MovieDBResponse>() {
            @Override
            public void onResponse(Call<MovieDBResponse> call, Response<MovieDBResponse> response) {
                MovieDBResponse movieDBResponse = response.body();


                if (movieDBResponse != null && movieDBResponse.getMovies() != null) {

                    movies = (ArrayList<Movie>) movieDBResponse.getMovies();
                    mutableLiveData.setValue(movies);
                }
            }

            @Override
            public void onFailure(Call<MovieDBResponse> call, Throwable t) {

            }
        });
        return mutableLiveData;
    }
}
