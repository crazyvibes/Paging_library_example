package in.crazyvibes.paging_library_example.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import in.crazyvibes.paging_library_example.R;
import in.crazyvibes.paging_library_example.databinding.MovieListItemBinding;
import in.crazyvibes.paging_library_example.ui.MovieActivity;
import in.crazyvibes.paging_library_example.ui.model.Movie;

public class MovieAdapter extends PagedListAdapter<Movie,MovieAdapter.MovieViewHolder> {
    private Context context;


    public MovieAdapter(Context context) {
        super(Movie.CALLBACK);
        this.context = context;

    }

    @NonNull
    @Override
    public MovieAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MovieListItemBinding movieListItemBinding= DataBindingUtil.inflate(LayoutInflater.from(parent.getContext())
                , R.layout.movie_list_item,parent,false);

        return new MovieViewHolder(movieListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.MovieViewHolder holder, int position) {
        Movie movie=getItem(position);
        holder.movieListItemBinding.setMovie(movie);
    }



    public class MovieViewHolder extends RecyclerView.ViewHolder {
        private MovieListItemBinding movieListItemBinding;
        public MovieViewHolder(@NonNull MovieListItemBinding movieListItemBinding) {
            super(movieListItemBinding.getRoot());

            this.movieListItemBinding=movieListItemBinding;

            movieListItemBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int position=getAdapterPosition();

                    if(position!=RecyclerView.NO_POSITION) {

                        Movie selctedMovie = getItem(position);

                        Intent intent=new Intent(context, MovieActivity.class);
                        intent.putExtra("movie",selctedMovie);
                        context.startActivity(intent);
                    }


                }
            });

        }
    }
}
