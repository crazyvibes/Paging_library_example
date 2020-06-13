package in.crazyvibes.paging_library_example.data.remote;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import in.crazyvibes.paging_library_example.ui.model.Movie;

public class MovieDataSource extends PageKeyedDataSource<Long, Movie> {
    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params, @NonNull LoadInitialCallback<Long, Movie> callback) {

    }

    @Override
    public void loadBefore(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Long, Movie> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Long, Movie> callback) {

    }
}
