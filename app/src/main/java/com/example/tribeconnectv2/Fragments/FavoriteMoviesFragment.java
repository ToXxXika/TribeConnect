package com.example.tribeconnectv2.Fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tribeconnectv2.DatabaseHandler.FirebaseHandler;
import com.example.tribeconnectv2.Models.Favorite;
import com.example.tribeconnectv2.R;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class FavoriteMoviesFragment extends Fragment {

    FirebaseFirestore db;
    List<Favorite> favoriteList = new ArrayList<>();

    private MovieFavoriteAdapter movieFavoriteAdapter;
    private RecyclerView recyclerView;
    public FavoriteMoviesFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite_movies, container, false);
        db = FirebaseFirestore.getInstance();
        FirebaseHandler  firebaseHandler= new FirebaseHandler();
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("user", getActivity().MODE_PRIVATE);
        firebaseHandler.getFavorites(db,sharedPreferences.getString("email",""),new FirebaseHandler.onGetFavoritesCallBack(){
            @Override
            public void onGetFavorites(List<Favorite> lstFavorites) {
                favoriteList = lstFavorites;
                recyclerView = view.findViewById(R.id.favorite_movies_recycler_view);
                int spanCount = getResources().getConfiguration().orientation == android.content.res.Configuration.ORIENTATION_LANDSCAPE ? 2 : 3;
                recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), spanCount));
                recyclerView.setAdapter(movieFavoriteAdapter);
                movieFavoriteAdapter = new MovieFavoriteAdapter(favoriteList, getActivity());
                recyclerView.setAdapter(movieFavoriteAdapter);

            }

        });
        return view;
    }
}
