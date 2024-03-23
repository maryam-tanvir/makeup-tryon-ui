package com.example.virtualmakeuptryonnew;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.List;

public class EyebrowListAdapter extends ArrayAdapter {

    private Activity mContext;
    List<Eyebrow> eyebrowList;

    public EyebrowListAdapter(Activity mContext, List<Eyebrow> eyebrowList) {
        super(mContext, R.layout.product_card, eyebrowList);
        this.mContext = mContext;
        this.eyebrowList = eyebrowList;
    }

    @NonNull
    @Override
    public View getView (int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = mContext.getLayoutInflater();
        View listItemView = inflater.inflate(R.layout.product_card,null,true);

        TextView lsName = listItemView.findViewById(R.id.productName);
        TextView lsPrice = listItemView.findViewById(R.id.productPrice);
        ImageView lsImage = listItemView.findViewById(R.id.productImage);

        Eyebrow eyebrow = eyebrowList.get(position);

        lsName.setText(eyebrow.getName());
        lsPrice.setText(eyebrow.getPrice());
        String imageUrl = eyebrow.getImage();
        Picasso.get().load(imageUrl).fit().into(lsImage);

        // Log the image URL for debugging
        Log.d("ListAdapter", "Image URL: " + imageUrl);

        // Placeholder and error handling (optional)
//        Picasso.get()
//                .load(imageUrl)
//                .placeholder(R.drawable.matt_lipstick) // Your placeholder image resource ID
//                .error(R.drawable.matt_lipstick) // Your error image resource ID
//                .into(lsImage);

        return listItemView;

    }
}
