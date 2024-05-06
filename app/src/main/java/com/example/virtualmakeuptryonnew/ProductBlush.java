package com.example.virtualmakeuptryonnew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ProductBlush extends AppCompatActivity {

    ListView myListView;
    List<Blush> blushList;
    DatabaseReference blushDbRef;
    ImageView back_btn;
    ProgressBar progressBar;
    Button tryonButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_product_blush);

        myListView = findViewById(R.id.myListView);
        blushList = new ArrayList<>();
        back_btn = findViewById(R.id.back_btn);
        tryonButton = findViewById(R.id.tryonButton);
        progressBar = findViewById(R.id.progressBar);


        blushDbRef = FirebaseDatabase.getInstance().getReference("Blush");

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        progressBar.setVisibility(View.VISIBLE);
        blushDbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                blushList.clear();

                for (DataSnapshot blushDatasnap : snapshot.getChildren()) {
                    Blush blush = blushDatasnap.getValue(Blush.class);
                    blushList.add(blush);
                }

                BlushListAdapter adapter = new BlushListAdapter(ProductBlush.this, blushList);
                myListView.setAdapter(adapter);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressBar.setVisibility(View.GONE);

            }
        });

        //set itemlong listener on listview item

//        myListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                Lipsticks lipsticks = lipstickslist.get(position);
//                showUpdateDialog()
//                return false;
//            }
//        });
    }
}