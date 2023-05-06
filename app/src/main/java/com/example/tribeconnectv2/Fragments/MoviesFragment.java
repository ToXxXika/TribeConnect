package com.example.tribeconnectv2.Fragments;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tribeconnectv2.Models.Movie;
import com.example.tribeconnectv2.R;

import java.util.ArrayList;
import java.util.List;

public class MoviesFragment extends Fragment {

    private List<Movie> movieList = new ArrayList<>();
    private RecyclerView recyclerView;
    private MoviesAdapter mAdapter;

    public MoviesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movies, container, false);
        movieList.add(new Movie("FRIENDS", R.drawable.placeholder_movie_poster));
        movieList.add(new Movie("How i met your mother", R.drawable.placeholder_movie_poster));
        movieList.add(new Movie("Lol", R.drawable.placeholder_movie_poster));
        movieList.add(new Movie("FRIENDS", R.drawable.placeholder_movie_poster));
        movieList.add(new Movie("How i met your mother", R.drawable.placeholder_movie_poster));
        movieList.add(new Movie("Lol", R.drawable.placeholder_movie_poster));
        movieList.add(new Movie("FRIENDS", R.drawable.placeholder_movie_poster));
        movieList.add(new Movie("How i met your mother", R.drawable.placeholder_movie_poster));
        movieList.add(new Movie("Lol", R.drawable.placeholder_movie_poster));
        recyclerView = view.findViewById(R.id.movies_recycler_view);
        mAdapter = new MoviesAdapter(movieList);
        int spanCount = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE ? 2 : 3;
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), spanCount));
        recyclerView.setAdapter(mAdapter);
        return view;


    }
}
