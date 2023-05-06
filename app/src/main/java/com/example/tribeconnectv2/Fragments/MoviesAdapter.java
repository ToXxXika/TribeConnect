package com.example.tribeconnectv2.Fragments;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tribeconnectv2.Models.Movie;
import com.example.tribeconnectv2.R;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder> {

    private List<Movie> movieList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, description;

        public MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.movie_title);
            description = view.findViewById(R.id.movie_description);
        }
    }


    public MoviesAdapter(List<Movie> movieList) {
        this.movieList = movieList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Movie movie = movieList.get(position);
        holder.title.setText(movie.getTitle());
        holder.description.setText(movie.getDescription());
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }
}
