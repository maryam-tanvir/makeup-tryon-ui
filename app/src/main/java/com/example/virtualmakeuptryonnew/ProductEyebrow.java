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

public class ProductEyebrow extends AppCompatActivity {

    ListView myListView;
    List<Eyebrow> eyebrowlist;
    DatabaseReference EyebrowDbRef;
    ImageView back_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_product_eyebrow);

        myListView = findViewById(R.id.myListView);
        eyebrowlist = new ArrayList<>();
        back_btn = findViewById(R.id.back_btn);

        EyebrowDbRef = FirebaseDatabase.getInstance().getReference("Eyebrow");

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        EyebrowDbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                eyebrowlist.clear();

                for (DataSnapshot eyebrowDatasnap : snapshot.getChildren()) {
                    Eyebrow eyebrow = eyebrowDatasnap.getValue(Eyebrow.class);
                    eyebrowlist.add(eyebrow);
                }

                EyebrowListAdapter adapter = new EyebrowListAdapter(ProductEyebrow.this, eyebrowlist);
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