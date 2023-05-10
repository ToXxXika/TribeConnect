package com.example.tribeconnectv2.Fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tribeconnectv2.Models.Movie;
import com.example.tribeconnectv2.R;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder> {

    private List<Movie> movieList;
    private MyViewHolder currentViewHolder;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        ImageView img;
        Button MovieButton;

        public MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.movie_title);
            img = view.findViewById(R.id.movie_poster);
            MovieButton = view.findViewById(R.id.movie_button);
            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (MovieButton.getVisibility() == View.VISIBLE) {
                        MovieButton.setVisibility(View.INVISIBLE);
                        currentViewHolder = null;
                    } else {
                        if (currentViewHolder != null) {
                            currentViewHolder.MovieButton.setVisibility(View.INVISIBLE);
                        }
                        MovieButton.setVisibility(View.VISIBLE);
                        Animation fadeInAnimation = AnimationUtils.loadAnimation(v.getContext(), R.anim.fade_in);
                        MovieButton.startAnimation(fadeInAnimation);
                        currentViewHolder = MyViewHolder.this;
                    }
                }
            });
        }
    }


    public MoviesAdapter(List<Movie> movieList, Context context) {
        this.movieList = movieList;
        this.context = context;
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
      /*  File f = new File("C:\\Users\\mabro\\IdeaProjects\\TribeConnect\\app\\src\\main\\res\\drawable\\" + movie.getImage() + ".png");
        Uri uri = Uri.fromFile(f);*/

        int resID = context.getResources().getIdentifier(movie.getImage() , "drawable", context.getPackageName());
        holder.img.setImageResource(resID);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }
}
