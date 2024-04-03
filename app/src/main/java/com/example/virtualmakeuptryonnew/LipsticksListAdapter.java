package com.example.virtualmakeuptryonnew;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.List;

public class LipsticksListAdapter extends ArrayAdapter {

    private Activity mContext;
    List<Lipsticks> lipstickList;

    public LipsticksListAdapter(Activity mContext, List<Lipsticks> lipstickList) {
        super(mContext, R.layout.product_card, lipstickList);
        this.mContext = mContext;
        this.lipstickList = lipstickList;
    }

    @NonNull
    @Override
    public View getView (int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = mContext.getLayoutInflater();
        View listItemView = inflater.inflate(R.layout.product_card,null,true);


        TextView lsName = listItemView.findViewById(R.id.productName);
        TextView lsPrice = listItemView.findViewById(R.id.productPrice);
        ImageView lsImage = listItemView.findViewById(R.id.productImage);
        Button tryOnButton = listItemView.findViewById(R.id.tryonButton);

        Lipsticks lipsticks = lipstickList.get(position);
        listItemView.setTag(lipsticks.getName());

        lsName.setText(lipsticks.getName());
        lsPrice.setText(lipsticks.getPrice());
        String imageUrl = lipsticks.getImage();
        Picasso.get().load(imageUrl).fit().into(lsImage);

        // Log the image URL for debugging
        Log.d("ListAdapter", "Image URL: " + imageUrl);

        tryOnButton.setText("Virtual Try-On");

        tryOnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String productName = (String) listItemView.getTag();

                Intent intent = LipsticksTryonIntents.getTryOnIntent(productName, mContext);
                if (intent != null) {
                    mContext.startActivity(intent);
                } else {
                    // Handle case where intent cannot be created (e.g., missing information)
                }
            }
        });

        // Placeholder and error handling (optional)
//        Picasso.get()
//                .load(imageUrl)
//                .placeholder(R.drawable.matt_lipstick) // Your placeholder image resource ID
//                .error(R.drawable.matt_lipstick) // Your error image resource ID
//                .into(lsImage);

        return listItemView;

    }
}
