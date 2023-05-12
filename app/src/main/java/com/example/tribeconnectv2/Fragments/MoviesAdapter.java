package com.example.tribeconnectv2.Fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Environment;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.*;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tribeconnectv2.DatabaseHandler.FirebaseHandler;
import com.example.tribeconnectv2.HomeActivity;
import com.example.tribeconnectv2.Models.* ;
import com.example.tribeconnectv2.R;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder> {

    private List<Movie> movieList;
    private MyViewHolder currentViewHolder;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        ImageView img;
        Button MovieButton;
        ImageButton starButton;

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        private void generatePDFandSendEmail(String mail ){
            // Create a new PDF document
            PdfDocument document = new PdfDocument();

            // Create a page and define its attributes

            int width = 300;
            int height = 500;
            int pageNumber = 1;
            PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(width, height, pageNumber).create();
            PdfDocument.Page page = document.startPage(pageInfo);


            // Create a canvas for drawing on the page
            Canvas canvas = page.getCanvas();
            Paint paint = new Paint();
            paint.setColor(Color.BLACK);
            paint.setTextSize(12f);

            canvas.drawText("Reservation details:", 50, 50, paint);

            document.finishPage(page);

            // Save the PDF document to a file
            File pdfFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), "reservation.pdf");
            try {
                document.writeTo(new FileOutputStream(pdfFile));
            } catch (IOException e) {
                e.printStackTrace();
            }
            document.close();

            // Send the PDF file via email
        }


        private void showReservationInputDialog(String mail,String movieTitle) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Number of Places");
            builder.setMessage("Enter the number of places for reservation");

            // Create an EditText view to get user input
            final EditText input = new EditText(context);
            input.setInputType(InputType.TYPE_CLASS_NUMBER);
            builder.setView(input);

            // Set the positive button
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String places = input.getText().toString();
                   FirebaseHandler firebaseHandler = new FirebaseHandler();
                   firebaseHandler.getMovies(db, new FirebaseHandler.getMoviesCallBack() {
                       @Override
                       public void onGetMovies(List<Movie> lstMovies) {
                            for (Movie movie : lstMovies) {
                                Log.d("movie",movie.getTitle());
                                Log.d("movie",movieTitle);
                                 if (movie.getTitle().equals(movieTitle)) {


                                     firebaseHandler.getsalles(db, new FirebaseHandler.getSallesCallBack() {
                                          @Override
                                          public void onGetSalles(List<Salle> lstSalles) {
                                              if(lstSalles == null){
                                                  Toast.makeText(context, "Reservation failed because there is no Salles", Toast.LENGTH_SHORT).show();
                                              }
                                              else{
                                                  for(Salle salle : lstSalles) {
                                                      if ((salle.getMovieId().equals(movie.getIdMovie())) && (salle.getPlaces() > Integer.parseInt(places))) {
                                                          Reservation reservation = new Reservation(mail, movie.getIdMovie(), Integer.parseInt(places));
                                                          firebaseHandler.bookMovie(reservation, salle, db, new FirebaseHandler.bookMovieCallBack() {
                                                              @Override
                                                              public void onBookMovie(boolean res) {
                                                                  if (res) {
                                                                      generatePDFandSendEmail(mail);
                                                                      Toast.makeText(context, "Reservation done", Toast.LENGTH_SHORT).show();
                                                                      return;

                                                                  } else {
                                                                      Toast.makeText(context, "Reservation failed", Toast.LENGTH_SHORT).show();
                                                                  }
                                                              }
                                                          });

                                                      }
                                                  }
                                              }
                                          }
                                      });


                                 }
                            }
                       }
                   });

                }
            });

            // Set the negative button
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            // Create and show the dialog
            AlertDialog dialog = builder.create();
            dialog.show();
        }

        public MyViewHolder(View view) {
            super(view);
            SharedPreferences sharedPreferences = context.getSharedPreferences("user", Context.MODE_PRIVATE);
            FirebaseHandler firebaseHandler = new FirebaseHandler();
            title = view.findViewById(R.id.movie_title);
            img = view.findViewById(R.id.movie_poster);
            MovieButton = view.findViewById(R.id.movie_button);
            starButton = view.findViewById(R.id.star_button);
            starButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //TODO:  change me with real Movie ID
                    firebaseHandler.LikeMovie(db,"M10" , sharedPreferences.getString("email", ""), new FirebaseHandler.LikeMovieCallBack() {
                        @Override
                        public void onLikeMovie(boolean res) {
                            if(res){
                                Toast.makeText(context, "Liked", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(context, "Already Liked", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });

                }
            });

            MovieButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
           /*      MovieDetailsFragment movieDetailsFragment = new MovieDetailsFragment();
                    FragmentManager fragmentManager = ((HomeActivity) context).getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.fragment_container, movieDetailsFragment).commit();*/
                    if (currentViewHolder != null) {
                        currentViewHolder.MovieButton.setVisibility(View.INVISIBLE);
                    }

                    AlertDialog.Builder builder = new AlertDialog.Builder(((HomeActivity)context));
                    builder.setTitle("Reservation");
                    builder.setMessage("Are you sure you want to reserve this movie?");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(context, currentViewHolder.title.getText(), Toast.LENGTH_SHORT).show();

                            showReservationInputDialog(sharedPreferences.getString("email", ""),currentViewHolder.title.getText().toString());
                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                          dialogInterface.dismiss();
                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                    Toast.makeText(context, "Movie Button Clicked", Toast.LENGTH_SHORT).show();
                }
                });
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

        int resID = context.getResources().getIdentifier(movie.getImage() , "drawable", context.getPackageName());
        holder.img.setImageResource(resID);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }
}
