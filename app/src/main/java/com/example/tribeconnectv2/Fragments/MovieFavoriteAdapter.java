package com.example.tribeconnectv2.Fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tribeconnectv2.DatabaseHandler.FirebaseHandler;
import com.example.tribeconnectv2.Models.Favorite;
import com.example.tribeconnectv2.Models.Movie;
import com.example.tribeconnectv2.R;
import com.google.firebase.firestore.FirebaseFirestore;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MovieFavoriteAdapter extends RecyclerView.Adapter<MovieFavoriteAdapter.MyViewHolder>{

    private List<Favorite> favoriteList;
    private MyViewHolder currentViewHolder;
    private Context context;
     private List<Movie> movieList;
     FirebaseFirestore db = FirebaseFirestore.getInstance();
    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView title;
        ImageView img ;




        public MyViewHolder(View view){
            super(view);
            title = view.findViewById(R.id.movie_title);
            img = view.findViewById(R.id.movie_poster);
            FirebaseHandler firebaseHandler = new FirebaseHandler();
            firebaseHandler.getMovies(db, new FirebaseHandler.getMoviesCallBack() {
                @Override
                public void onGetMovies(List<Movie> lstMovies) {
                    if(lstMovies.isEmpty()){
                        Toast.makeText(context, "No  Movies", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    movieList = lstMovies;
                }
            });
        }

    }
    public MovieFavoriteAdapter(List<Favorite> favoriteList, Context context){
        this.favoriteList = favoriteList;
        this.context = context;
    }
    @NonNull
    @NotNull
    @Override
    public MovieFavoriteAdapter.MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_card, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MovieFavoriteAdapter.MyViewHolder holder, int position) {
           Favorite favorite = favoriteList.get(position);
        for (Movie movie : movieList
             ) {
             if(movie.getIdMovie().equals(favorite.getIdMovie())){
                 holder.title.setText(movie.getTitle());
                 int resID = context.getResources().getIdentifier(movie.getImage(), "drawable", context.getPackageName());
                 holder.img.setImageResource(resID);
             }
        }


    }

    @Override
    public int getItemCount() {
        return favoriteList.size();
    }
}
