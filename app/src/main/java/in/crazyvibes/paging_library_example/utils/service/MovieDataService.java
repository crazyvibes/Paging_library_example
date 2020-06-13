package in.crazyvibes.paging_library_example.utils.service;

import in.crazyvibes.paging_library_example.data.remote.MovieDBResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieDataService {


    @GET("movie/popular")
    Call<MovieDBResponse> getPopularMovies(@Query("api_key") String apiKey);


    @GET("movie/popular")
    Call<MovieDBResponse> getPopularMoviesWithPaging(
            @Query("api_key") String apiKey,
            @Query("page") long page);

}
