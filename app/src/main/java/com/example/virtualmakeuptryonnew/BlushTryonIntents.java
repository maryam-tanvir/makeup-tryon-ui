package com.example.virtualmakeuptryonnew;

import android.content.Context;
import android.content.Intent;

public class BlushTryonIntents {

    public static Intent getTryOnIntent(String productName, Context context) {
        switch (productName) {
            case "Brown Mascara":
                return new Intent(context, PinkBlushTryon.class);
            case "Blue Mascara":
                return new Intent(context, KajalTestApi.class);
            case "Brown lipstick":
                return new Intent(context, BrownLipstickTryon.class);
            case "Maroon Lipstick":
                return new Intent(context, MaroonLipstickTryon.class);
            case "Purple Lipstick":
                return new Intent(context, PurpleLipstickTryon.class);
            // ... (add more cases as needed)
            default:
                return null; // Or provide a default intent if needed
        }
    }
}
