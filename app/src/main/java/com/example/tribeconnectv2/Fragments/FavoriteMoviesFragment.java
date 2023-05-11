package com.example.tribeconnectv2.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.example.tribeconnectv2.R;

public class FavoriteMoviesFragment extends Fragment {

    public FavoriteMoviesFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite_movies, container, false);
        return view;
    }
}
