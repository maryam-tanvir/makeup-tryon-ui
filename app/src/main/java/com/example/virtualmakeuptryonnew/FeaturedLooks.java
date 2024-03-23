package com.example.virtualmakeuptryonnew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.ImageView;

public class FeaturedLooks extends AppCompatActivity {

    ImageView back_btn, partyMakeup, simpleMakeup, formalMakeup, bridalMakeup;
    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_featured_looks);

        back_btn = findViewById(R.id.back_btn);
        partyMakeup = findViewById(R.id.partyMakeup);
        simpleMakeup = findViewById(R.id.simpleMakeup);
        formalMakeup = findViewById(R.id.formalMakeup);
        bridalMakeup = findViewById(R.id.bridalMakeup);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        partyMakeup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TryOptions.class);
                startActivity(intent);
            }
        });

        simpleMakeup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TryOptions.class);
                startActivity(intent);
            }
        });

        formalMakeup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TryOptions.class);
                startActivity(intent);
            }
        });

        bridalMakeup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TryOptions.class);
                startActivity(intent);
            }
        });
    }
}