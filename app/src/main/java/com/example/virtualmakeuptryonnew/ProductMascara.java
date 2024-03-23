package com.example.virtualmakeuptryonnew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ProductMascara extends AppCompatActivity {

    ListView myListView;
    List<Mascara> mascaralist;
    DatabaseReference MascaraDbRef;
    ImageView back_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_product_mascara);

        myListView = findViewById(R.id.myListView1);
        mascaralist = new ArrayList<>();
        back_btn = findViewById(R.id.back_btn);

        MascaraDbRef = FirebaseDatabase.getInstance().getReference("Eyelashes");

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        MascaraDbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mascaralist.clear();

                for (DataSnapshot MascaraDatasnap : snapshot.getChildren()) {
                    Mascara mascara = MascaraDatasnap.getValue(Mascara.class);
                    mascaralist.add(mascara);
                }

                MascaraListAdapter adapter = new MascaraListAdapter(ProductMascara.this, mascaralist);
                myListView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}