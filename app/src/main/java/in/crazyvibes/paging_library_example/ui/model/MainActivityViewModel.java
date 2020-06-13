package in.crazyvibes.paging_library_example.ui.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import in.crazyvibes.paging_library_example.data.remote.MovieDataSource;
import in.crazyvibes.paging_library_example.data.remote.MovieDataSourceFactory;
import in.crazyvibes.paging_library_example.data.repository.MovieRepository;
import in.crazyvibes.paging_library_example.utils.service.MovieDataService;
import in.crazyvibes.paging_library_example.utils.service.RetrofitInstance;

public class MainActivityViewModel extends AndroidViewModel {
    private MovieRepository movieRepository;

    /**paging library code*/
    LiveData<MovieDataSource> movieDataSourceLiveData;
    private Executor executor;/** Android has an Executor framework using which you can automatically manage a pool of threads with a task queue.
     multiple threads will run in parallel executing tasks from the queue, we have to initialize an executor class here for the paged list.*/
    private LiveData<PagedList<Movie>> moviesPagedList;


    public MainActivityViewModel(@NonNull Application application) {
        super(application);

        movieRepository=new MovieRepository(application);


        /**paging library code*/
        MovieDataService movieDataService= RetrofitInstance.getService();
        MovieDataSourceFactory factory=new MovieDataSourceFactory(movieDataService,application);
        movieDataSourceLiveData=factory.getMutableLiveData();

        PagedList.Config config=(new PagedList.Config.Builder())
                .setEnablePlaceholders(true) //enable until will be data load
                .setInitialLoadSizeHint(10) //the numbers of items load initially
                .setPageSize(20) //the number of items to load in the  page list
                .setPrefetchDistance(4) //the number of pages to load initially when the screen load
                .build();

        executor= Executors.newFixedThreadPool(5);

        moviesPagedList = (new LivePagedListBuilder<Long,Movie>(factory,config))
                .setFetchExecutor(executor)
                .build();
    }

    public LiveData<List<Movie>> getAllMovies(){

        return movieRepository.getMutableLiveData();
    }

    /**paging library code*/
    public LiveData<PagedList<Movie>> getMoviesPagedList() {
        return moviesPagedList;
    }

}
