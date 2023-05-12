package com.example.tribeconnectv2;

import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.example.tribeconnectv2.Fragments.FavoriteMoviesFragment;
import com.example.tribeconnectv2.Fragments.MoviesFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.FirebaseFirestore;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    TextView name;
    ActionBarDrawerToggle toggle;

    FirebaseFirestore db;

    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = FirebaseFirestore.getInstance();
        setContentView(R.layout.activity_home);
        Objects.requireNonNull(getSupportActionBar()).hide();

        Bundle bundle = getIntent().getExtras();
        String username = "";
        if (bundle != null) {
            username = bundle.getString("admin");
            Toast toast = Toast.makeText(getApplicationContext(), username, Toast.LENGTH_SHORT);
            toast.show();
            System.out.println(username);
        }
        name = findViewById(R.id.txtName);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);
        name = headerView.findViewById(R.id.txtName);
        name.setText(username);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        MoviesFragment fragment = new MoviesFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.fragment_container, fragment).commit();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);


    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(navigationView)) {
            drawerLayout.closeDrawer(navigationView);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
        int id = item.getItemId();
        switch (id){


            case R.id.nav_home:
                MoviesFragment moviesFragment2 = new MoviesFragment();
                FragmentManager FM2 = getSupportFragmentManager();
                FM2.beginTransaction().replace(R.id.fragment_container, moviesFragment2).commit();
                Toast.makeText(this, "Movies", Toast.LENGTH_SHORT).show();
                break;
            case R.id.fav:
                FavoriteMoviesFragment fragment = new FavoriteMoviesFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();
                Toast.makeText(this, "Movies", Toast.LENGTH_SHORT).show();
                break;
            case R.id.Logout:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                //TODO : dont forget to empty the shared preferences
                finish();
            default:
                return true;
        }
        return false ;
    }
}