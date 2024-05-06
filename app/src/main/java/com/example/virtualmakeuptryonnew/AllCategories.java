package com.example.virtualmakeuptryonnew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

public class AllCategories extends AppCompatActivity {

    ImageView back_btn, lipstickCategory, blushCategory, lensCategory, lashesCategory, kajalCategory;
    Button viewProducts1, viewProducts2, viewProducts3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_all_categories);

        // hooks
        back_btn = findViewById(R.id.back_btn);
        lipstickCategory = findViewById(R.id.lipstickCategory);
        blushCategory = findViewById(R.id.blushCategory);
        lensCategory = findViewById(R.id.lensCategory);
        //lashesCategory = findViewById(R.id.eyelashesCategory);
        kajalCategory = findViewById(R.id.kajalCategory);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Dashboard.class);
                startActivity(intent);
            }
        });

        lipstickCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProductLipsticks.class);
                startActivity(intent);
            }
        });

        blushCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProductBlush.class);
                startActivity(intent);
            }
        });

        lensCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProductLens.class);
                startActivity(intent);
            }
        });

        kajalCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProductKajal.class);
                startActivity(intent);
            }
        });
    }
}