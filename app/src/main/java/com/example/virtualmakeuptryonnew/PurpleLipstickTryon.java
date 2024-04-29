package com.example.virtualmakeuptryonnew;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class PurpleLipstickTryon extends AppCompatActivity {

    private static final int REQUEST_IMAGE_GALLERY = 100;
    private Button buttonOpenGallery, buyProduct;
    private ImageView imageView;
    ProgressBar progressBar;
    ImageView back_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_purple_lipstick_tryon);

        buttonOpenGallery = findViewById(R.id.buttonOpenGallery);
        buyProduct = findViewById(R.id.buyProduct);
        imageView = findViewById(R.id.imageView);
        progressBar = findViewById(R.id.progressBar);
        back_btn = findViewById(R.id.back_btn);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        buttonOpenGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openGallery();
                progressBar.setVisibility(View.VISIBLE);
            }

        });

        buyProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // URL to open
                String url = "https://cozmetica.pk/products/la-girl-lip-attraction-2-lipstick-sugar-plum?pr_prod_strat=e5_desc&pr_rec_id=38d76d210&pr_rec_pid=7750112116897&pr_ref_pid=7750111953057&pr_seq=uniform";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });
    }

    private void openGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, REQUEST_IMAGE_GALLERY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_GALLERY && resultCode == RESULT_OK && data != null) {
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());
                imageView.setImageBitmap(bitmap);
                imageView.setVisibility(View.VISIBLE);

                // Make POST request with bitmap
                makePostRequest(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void makePostRequest(final Bitmap bitmap) {
//        OkHttpClient client = new OkHttpClient();

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();

        // Convert bitmap to byte array
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] imageBytes = byteArrayOutputStream.toByteArray();

        // Create multipart request body
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", "image.png", RequestBody.create(MediaType.parse("image/*"), imageBytes))
                .build();

        String baseUrl = getResources().getString(R.string.apiUrl);
        // Create POST request
        Request request = new Request.Builder()
                .url(baseUrl + "/apply-makeup/?choice=lips&red=160&green=32&blue=240")
                .post(requestBody)
                .build();

        // Execute the request asynchronously
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                Log.e("TAG", "onResponse: ");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        if (response.body()!=null)
                        {
                            try {

                                ByteArrayInputStream stream = new ByteArrayInputStream(response.body().bytes());
                                Bitmap img = BitmapFactory.decodeStream(stream);
                                imageView.setImageBitmap(img);
                                progressBar.setVisibility(View.GONE);

                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                });
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }
        });
    }
}