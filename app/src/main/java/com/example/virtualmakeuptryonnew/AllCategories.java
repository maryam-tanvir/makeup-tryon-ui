package com.example.virtualmakeuptryonnew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

public class AllCategories extends AppCompatActivity {

    ImageView back_btn;
    Button viewProducts1, viewProducts2, viewProducts3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_all_categories);

        // hooks
        back_btn = findViewById(R.id.back_btn);
        viewProducts1 = findViewById(R.id.viewProducts1);
        viewProducts2 = findViewById(R.id.viewProducts2);
        viewProducts3 = findViewById(R.id.viewProducts3);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Dashboard.class);
                startActivity(intent);
            }
        });

        viewProducts1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProductLipsticks.class);
                startActivity(intent);
            }
        });

        viewProducts2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProductMascara.class);
                startActivity(intent);
            }
        });

        viewProducts3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProductEyebrow.class);
                startActivity(intent);
            }
        });
    }
}