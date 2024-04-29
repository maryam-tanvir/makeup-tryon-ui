package com.example.virtualmakeuptryonnew;

import android.content.Context;
import android.content.Intent;

public class LensTryonIntents {

    public static Intent getTryOnIntent(String productName, Context context) {
        switch (productName) {
            case "Amber Grey Lens":
                return new Intent(context, AmberGreyLensTryon.class);
            case "Hazel Lens":
                return new Intent(context, HazelLensTryon.class);
            case "Edge Brown Lens":
                return new Intent(context, EdgeBrownLensTryon.class);
            case "Highlight Blue Lens":
                return new Intent(context, HighlightBlueLensTryon.class);
            case "Sky Caramel Lens":
                return new Intent(context, SkyCaramelLensTryon.class);
            // ... (add more cases as needed)
            default:
                return null; // Or provide a default intent if needed
        }
    }
}
