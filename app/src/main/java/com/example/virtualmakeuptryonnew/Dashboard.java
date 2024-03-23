package com.example.virtualmakeuptryonnew;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
public class Dashboard extends AppCompatActivity {
    RecyclerView featuredRecycler, categoriesRecycler;
    Button logout_btn;
    DrawerLayout drawerLayout;
    Button viewAll;
    RecyclerView.Adapter adapter;
    ImageView lipstickIcon, mascaraIcon, eyebrowIcon, featuredLooks;
    private GradientDrawable gradient1, gradient2, gradient3, gradient4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dashboard);

        ImageView menuImage = findViewById(R.id.menuImage);

        NavigationView navigationView = findViewById(R.id.navigationView);
        View headerView = navigationView.getHeaderView(0);

        drawerLayout = findViewById(R.id.drawer_layout);
        logout_btn = headerView.findViewById(R.id.logout_btn);
        viewAll = findViewById(R.id.viewAllButton);
        lipstickIcon = findViewById(R.id.lipsticksIcon);
        mascaraIcon = findViewById(R.id.mascaraIcon);
        eyebrowIcon = findViewById(R.id.eyebrowIcon);
        featuredLooks = findViewById(R.id.featuredLooksImage);

        //Hooks
        featuredRecycler = findViewById(R.id.featured_recycler);
        categoriesRecycler = findViewById(R.id.categories_recycler);
        //Functions will be executed automatically when this activity will be created
        featuredRecycler();
        categoriesRecycler();

        // using firebase for logout functionality
        FirebaseUser user;
        FirebaseAuth auth;

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        if (user == null) {
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
        }
        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
            }
        });

        menuImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
            }
        });

        viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AllCategories.class);
                startActivity(intent);
            }
        });

        lipstickIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProductLipsticks.class);
                startActivity(intent);
            }
        });

        mascaraIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProductMascara.class);
                startActivity(intent);
            }
        });

        eyebrowIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProductEyebrow.class);
                startActivity(intent);
            }
        });

        featuredLooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FeaturedLooks.class);
                startActivity(intent);
            }
        });

    }
    private void categoriesRecycler() {
        //All Gradients
        gradient2 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xff95CCE4, 0xff95CCE4});
        gradient1 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffDAAEBD, 0xffDAAEBD});
        gradient3 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xff9AE3DC, 0xff9AE3DC});
        gradient4 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffb8d7f5, 0xffb8d7f5});
        ArrayList<CategoriesHelperClass> categoriesHelperClasses = new ArrayList<>();
        categoriesHelperClasses.add(new CategoriesHelperClass(gradient1, R.drawable.lipstickcategory, "Lipstick"));
        categoriesHelperClasses.add(new CategoriesHelperClass(gradient2, R.drawable.mascaracategory, "Mascara"));
        categoriesHelperClasses.add(new CategoriesHelperClass(gradient3, R.drawable.eyebrowcategory, "Eyebrows"));
        categoriesHelperClasses.add(new CategoriesHelperClass(gradient4, R.drawable.lipstick, "Blush"));
        categoriesHelperClasses.add(new CategoriesHelperClass(gradient1, R.drawable.lipstick, "Foundation"));
        categoriesRecycler.setHasFixedSize(true);
        adapter = new CategoriesAdapter(categoriesHelperClasses);
        categoriesRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        categoriesRecycler.setAdapter(adapter);
    }


    private void featuredRecycler() {
        featuredRecycler.setHasFixedSize(true);
        featuredRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        ArrayList<FeaturedHelperClass> featuredProducts = new ArrayList<>();
        featuredProducts.add(new FeaturedHelperClass(R.drawable.matt_lipstick, "Matte Lipstick", "Lorem opium lorem opium lorem opium "));
        featuredProducts.add(new FeaturedHelperClass(R.drawable.bluemascara, "Blue Mascara", "Lorem opium lorem opium lorem opium "));
        featuredProducts.add(new FeaturedHelperClass(R.drawable.browneyebrow, "Brown Eyebrow Pencil", "Lorem opium lorem opium lorem opium "));
        adapter = new FeaturedAdapter(featuredProducts);
        featuredRecycler.setAdapter(adapter);
    }
}