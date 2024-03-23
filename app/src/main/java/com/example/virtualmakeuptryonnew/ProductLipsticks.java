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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ProductLipsticks extends AppCompatActivity {

    ListView myListView;
    List<Lipsticks> lipstickslist;
    DatabaseReference lipsticksDbRef;
    ImageView back_btn;

    Button tryonButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_product_lipsticks);

        myListView = findViewById(R.id.myListView);
        lipstickslist = new ArrayList<>();
        back_btn = findViewById(R.id.back_btn);
        tryonButton = findViewById(R.id.tryonButton);


        lipsticksDbRef = FirebaseDatabase.getInstance().getReference("Lipsticks");

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        lipsticksDbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                lipstickslist.clear();

                for (DataSnapshot lipsticksDatasnap : snapshot.getChildren()) {
                    Lipsticks lipsticks = lipsticksDatasnap.getValue(Lipsticks.class);
                    lipstickslist.add(lipsticks);
                }

                LipsticksListAdapter adapter = new LipsticksListAdapter(ProductLipsticks.this, lipstickslist);
                myListView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

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