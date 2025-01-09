package com.example.angrybird;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowInsetsController;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

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
    private String orientation = "portrait";

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
        setupAnimation();
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        setContentView(R.layout.activity_main); // Re-initialize UI elements after orientation change
        setupButtonListeners();
        setupAnimation();

    }

    @SuppressLint("ClickableViewAccessibility")
    private void setupButtonListeners() {
        // Handle button in portrait layout
        Button btnLandscape = findViewById(R.id.button2);
        if (btnLandscape != null) {
            btnLandscape.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    orientation = "landscape";
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                }
            });
        }

        if(orientation.equals("landscape")){

            ImageView imageView = findViewById(R.id.imageView2);
            imageView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {



                    return false;
                }
            });
        }
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

    private void setupAnimation(){
        if(orientation.equals("landscape")){

            Animation animation = AnimationUtils.loadAnimation(
                    MainActivity.this,
                    R.anim.before_shoot
            );
            ImageView imageView = findViewById(R.id.imageView2);
            imageView.startAnimation(animation);

            new Handler().postDelayed(
                    new Runnable() {
                        @Override
                        public void run() {

                            Animation animation2 = AnimationUtils.loadAnimation(
                                    MainActivity.this,
                                    R.anim.before_shoot
                            );

                            ImageView imageView2 = findViewById(R.id.imageView3);
                            imageView2.startAnimation(animation2);
                        }
                    }
                    , 1000
            );

        }
    }
}