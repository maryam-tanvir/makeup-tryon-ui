package com.example.virtualmakeuptryonnew;

import android.content.Context;
import android.content.Intent;

public class MascaraTryonIntents {

    public static Intent getTryOnIntent(String productName, Context context) {
        switch (productName) {
            case "Brown Mascara":
                return new Intent(context, DustyPinkBlushTryon.class);
            case "Blue Mascara":
                return new Intent(context, MaltBlushTryon.class);
            case "Purple Mascara":
                return new Intent(context, RoseBlushTryon.class);
            case "Navy Blue Mascara":
                return new Intent(context, RustyBrownBlushTryon.class);
            case "Green Mascara":
                return new Intent(context, SparklingPinkBlushTryon.class);
            // ... (add more cases as needed)
            default:
                return null; // Or provide a default intent if needed
        }
    }
}
