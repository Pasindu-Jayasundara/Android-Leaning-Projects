package com.example.angrybird;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowInsetsController;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private String language = "en";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WindowInsetsControllerCompat windowInsetsController = WindowCompat.getInsetsController(getWindow(), getWindow().getDecorView());
        windowInsetsController.hide(WindowInsetsCompat.Type.systemBars());
        windowInsetsController.setSystemBarsBehavior(WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setupButtonListeners();
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        setLanguage(language); // Ensure language is set on configuration change
        setContentView(R.layout.activity_main); // Re-initialize UI elements after orientation change
        setupButtonListeners();

    }

    private void setupButtonListeners() {
        // Handle button in portrait layout
        Button btnLandscape = findViewById(R.id.button2);
        if (btnLandscape != null) {
            btnLandscape.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setLanguage("si");  // Set Sinhala on button click
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                }
            });
        }

        // Handle button in landscape layout
//        Button btnPortrait = findViewById(R.id.button);
//        if (btnPortrait != null) {
//            btnPortrait.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    setLanguage("en");  // Set Sinhala on button click
//                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//                }
//            });
//        }
    }

    private void setLanguage(String languageCode) {

        language = languageCode;

        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);
        Resources resources = getResources();
        Configuration config = resources.getConfiguration();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();

        // For newer versions of Android
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            config.setLocale(locale);
        } else {
            config.locale = locale; // For older versions
        }

        resources.updateConfiguration(config, displayMetrics);  // Update the resources with the new locale
    }
}