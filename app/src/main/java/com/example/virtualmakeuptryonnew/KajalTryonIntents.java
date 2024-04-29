package com.example.virtualmakeuptryonnew;

import android.content.Context;
import android.content.Intent;

public class KajalTryonIntents {

    public static Intent getTryOnIntent(String productName, Context context) {
        switch (productName) {
            case "Grey Kajal Pencil":
                return new Intent(context, GreyKajalTryon.class);
            case "Black Kajal Pencil":
                return new Intent(context, BlackKajalTryon.class);
            case "Blue Kajal Pencil":
                return new Intent(context, BlueKajalTryon.class);
            case "Brown Kajal Pencil":
                return new Intent(context, BrownKajalTryon.class);
            case "Magenta Kajal Pencil":
                return new Intent(context, MagentaKajalTryon.class);
            // ... (add more cases as needed)
            default:
                return null; // Or provide a default intent if needed
        }
    }
}
