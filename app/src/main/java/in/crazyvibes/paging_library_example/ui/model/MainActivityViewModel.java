package in.crazyvibes.paging_library_example.ui.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import in.crazyvibes.paging_library_example.data.repository.MovieRepository;

public class MainActivityViewModel extends AndroidViewModel {
    private MovieRepository movieRepository;
    public MainActivityViewModel(@NonNull Application application) {
        super(application);

        movieRepository=new MovieRepository(application);
    }

    public LiveData<List<Movie>> getAllMovies(){

        return movieRepository.getMutableLiveData();
    }
}
