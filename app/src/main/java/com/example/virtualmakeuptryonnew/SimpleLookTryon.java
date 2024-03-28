package com.example.virtualmakeuptryonnew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
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

public class SimpleLookTryon extends AppCompatActivity {

    Button selectImageButton;
    ImageView displayImage, back_btn;



    private static final int PICK_IMAGE_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_simple_look_tryon);

        selectImageButton = findViewById(R.id.selectImageButton);
        displayImage = findViewById(R.id.displayImage);
        back_btn = findViewById(R.id.back_btn);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        selectImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri selectedImageUri = data.getData();

            // **Optional: Image conversion (if needed)**
            // String encodedImage = convertImageToBase64(selectedImageUri);

            // Prepare API request parameters
            String tasks = "Apply Black Eyes Shadow"; // Replace with your API task(s)
            String basePrompt = "While keeping image realistic and without altering the original image"; // Replace with your API base prompt

            // **API call using Volley or another library**
            sendImageToApi(selectedImageUri, tasks, basePrompt);
        }
    }

    private void sendImageToApi(Uri imageUri, String tasks, String basePrompt) {
        try {
            // Get the image as a byte array
            byte[] imageBytes = getByteArrayFromUri(imageUri);

            // Encode image bytes to base64 string
            String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);

            // Add encoded image to params
            Map<String, String> params = new HashMap<>();
            params.put("image", encodedImage);
            params.put("tasks", tasks);
            params.put("base_prompt", basePrompt);

            // API endpoint URL (replace with your actual API URL)
            String yourApiUrl = "https://d23a-34-91-195-146.ngrok-free.app/generate_image/";

            // Create a StringRequest to send the data
            StringRequest request = new StringRequest(Request.Method.POST, yourApiUrl,
                    response -> {
                        displayProcessedImage(response);  // Call method to display processed image
                    },
                    error -> {
                        // Handle API call error
                        Toast.makeText(SimpleLookTryon.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("image", encodedImage);
                    params.put("tasks", tasks);
                    params.put("base_prompt", basePrompt);
                    return params;
                }
            };

            // Add request to request queue
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(request);
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(SimpleLookTryon.this, "Error: Could not read image", Toast.LENGTH_SHORT).show();
        }
    }

    // Helper method to get byte array from Uri
    private byte[] getByteArrayFromUri(Uri uri) throws IOException {
        InputStream inputStream = getContentResolver().openInputStream(uri);
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, bytesRead);
        }
        return byteBuffer.toByteArray();
    }

    private void displayProcessedImage(String response) {
        // Parse the JSON response to get the processed image URL (or data)
        try {
            JSONObject jsonObject = new JSONObject(response);
            String processedImageUrl = jsonObject.getString("processed_image_url");  // Replace with your response key
            Glide.with(this).load(processedImageUrl).into(displayImage);  // Use Glide to load the image
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(SimpleLookTryon.this, "Error: Processing failed", Toast.LENGTH_SHORT).show();
        }
    }
}

