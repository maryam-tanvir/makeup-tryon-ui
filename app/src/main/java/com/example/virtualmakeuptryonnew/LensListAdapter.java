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

public class LensListAdapter extends ArrayAdapter {

    private Activity mContext;
    List<Lens> lensList;

    public LensListAdapter(Activity mContext, List<Lens> lensList) {
        super(mContext, R.layout.product_card, lensList);
        this.mContext = mContext;
        this.lensList = lensList;
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

        Lens lens = lensList.get(position);
        listItemView.setTag(lens.getName());

        lsName.setText(lens.getName());
        lsPrice.setText(lens.getPrice());
        String imageUrl = lens.getImage();
        Picasso.get().load(imageUrl).fit().into(lsImage);

        // Log the image URL for debugging
        Log.d("ListAdapter", "Image URL: " + imageUrl);

        tryOnButton.setText("Virtual Try-On");

        tryOnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String productName = (String) listItemView.getTag();

                Intent intent = LensTryonIntents.getTryOnIntent(productName, mContext);
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
