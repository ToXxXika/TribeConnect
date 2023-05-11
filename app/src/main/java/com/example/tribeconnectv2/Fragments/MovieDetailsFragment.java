package com.example.tribeconnectv2.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.example.tribeconnectv2.R;

public class MovieDetailsFragment extends Fragment {

    ImageView imgMovie;
    TextView txtTitle, txtGenre, txtDate, txtDescription, txtDuration, txtRating, txtPrice;
    public MovieDetailsFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);
        imgMovie = view.findViewById(R.id.imageViewMoviePoster);
        txtTitle = view.findViewById(R.id.textViewMovieTitle);
        txtGenre = view.findViewById(R.id.textViewGenre);
        txtDate = view.findViewById(R.id.textViewReleaseYear);
        txtDescription = view.findViewById(R.id.textViewDescription);

        return view;
    }
}
