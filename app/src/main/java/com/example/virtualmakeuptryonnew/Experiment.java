package com.example.virtualmakeuptryonnew;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class Experiment extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private static final String TAG = "ExperimentActivity";

    private Button selectImageButton;
    private ImageView displayImage, back_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_experiment);

        selectImageButton = findViewById(R.id.selectImageButton);
        displayImage = findViewById(R.id.displayImage);
        back_btn = findViewById(R.id.back_btn);

        back_btn.setOnClickListener(v -> finish());

        selectImageButton.setOnClickListener(v -> openGallery());
    }

    private void openGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri fileUri = data.getData();
            sendImageToApi(fileUri);
        }
    }

    private void sendImageToApi(Uri imageUri) {
        try {
            byte[] imageBytes = getByteArrayFromUri(imageUri);
            String apiUrl = "https://a69d-34-139-133-37.ngrok-free.app/apply-makeup/?choice=lips";

            StringRequest request = new StringRequest(Request.Method.POST, apiUrl,
                    this::handleResponse,
                    this::handleError) {
                @Override
                public byte[] getBody() throws AuthFailureError {
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    try {
                        bos.write("*".getBytes());
                        bos.write(imageBytes);
                    } catch (IOException e) {
                        Log.e(TAG, "Error converting image to byte array", e);
                    }
                    return bos.toByteArray();
                }

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> headers = new HashMap<>();
                    headers.put("Content-Type", "application/octet-stream");
                    return headers;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(request);
        } catch (IOException e) {
            Log.e(TAG, "Error reading image from URI", e);
            Toast.makeText(this, "Error: Could not read image", Toast.LENGTH_SHORT).show();
        }
    }

    private byte[] getByteArrayFromUri(Uri uri) throws IOException {
        InputStream inputStream = getContentResolver().openInputStream(uri);
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, bytesRead);
        }
        return byteBuffer.toByteArray();
    }

    private void handleResponse(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            String processedImageUrl = jsonObject.getString("processed_image_url");
            displayProcessedImage(processedImageUrl);
        } catch (JSONException e) {
            Log.e(TAG, "Error parsing JSON response", e);
            Toast.makeText(this, "Error: Processing failed", Toast.LENGTH_SHORT).show();
        }
    }

    private void handleError(VolleyError error) {
        Log.e(TAG, "Error making API request", error);
        Toast.makeText(this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
    }

    private void displayProcessedImage(String imageUrl) {
        Glide.with(this).load(imageUrl).into(displayImage);
    }
}
