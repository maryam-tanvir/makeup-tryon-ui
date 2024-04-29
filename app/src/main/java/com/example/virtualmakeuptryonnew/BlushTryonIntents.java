package com.example.virtualmakeuptryonnew;

import android.content.Context;
import android.content.Intent;

public class BlushTryonIntents {

    public static Intent getTryOnIntent(String productName, Context context) {
        switch (productName) {
            case "Dusty Pink Blush":
                return new Intent(context, DustyPinkBlushTryon.class);
            case "Malt Blush":
                return new Intent(context, MaltBlushTryon.class);
            case "Rose Blush":
                return new Intent(context, RoseBlushTryon.class);
            case "Rusty Brown Blush":
                return new Intent(context, RustyBrownBlushTryon.class);
            case "Sparkling Pink Blush":
                return new Intent(context, SparklingPinkBlushTryon.class);
            // ... (add more cases as needed)
            default:
                return null; // Or provide a default intent if needed
        }
    }
}
