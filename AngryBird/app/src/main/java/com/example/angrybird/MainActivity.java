package com.example.angrybird;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowInsetsController;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
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

import com.example.angrybird.model.Bird;

import java.lang.reflect.Field;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private String language = "en";
    private String orientation = "portrait";
    private float slingShotX = 0f;
    private float slingShotY = 0f;
    private ImageView currentBird;
    private Bird angryBird;
    private Bird fastBird;

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

        setupDimentions();
        setupButtonListeners();
        setupAnimation();
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        setContentView(R.layout.activity_main);
        setupButtonListeners();
        setupAnimation();
        setupDimentions();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setupButtonListeners() {

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
            ImageView imageView3 = findViewById(R.id.imageView3);
            // angry bird
            imageView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {

                    view.clearAnimation();

                    if(currentBird==null || currentBird != imageView){

                        if(currentBird!=null){

                            ObjectAnimator rotateAnimator = ObjectAnimator.ofFloat(imageView3, "rotation", 360f, 0f);
                            rotateAnimator.setDuration(1000);

                            ObjectAnimator translateXAnimator = ObjectAnimator.ofFloat(imageView3, "translationX", -fastBird.getInitialX());
                            translateXAnimator.setDuration(1000);

                            ObjectAnimator translateYAnimator = ObjectAnimator.ofFloat(imageView3, "translationY", -fastBird.getInitialY());
                            translateYAnimator.setDuration(1000);

                            AnimatorSet animatorSet = new AnimatorSet();
                            animatorSet.playTogether(rotateAnimator, translateXAnimator, translateYAnimator);
                            animatorSet.start();

                        }

                        ObjectAnimator rotateAnimator = ObjectAnimator.ofFloat(imageView, "rotation", 0f, 360f);
                        rotateAnimator.setDuration(1000);

                        ObjectAnimator translateXAnimator = ObjectAnimator.ofFloat(imageView, "translationX", slingShotX);
                        translateXAnimator.setDuration(1000);

                        ObjectAnimator translateYAnimator = ObjectAnimator.ofFloat(imageView, "translationY", slingShotY);
                        translateYAnimator.setDuration(1000);

                        AnimatorSet animatorSet = new AnimatorSet();
                        animatorSet.playTogether(rotateAnimator, translateXAnimator, translateYAnimator);
                        animatorSet.start();

                        currentBird = imageView;
                    }

                    return true;
                }
            });

            // fast bird
            imageView3.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {

                    view.clearAnimation();

                    if(currentBird==null || currentBird != imageView3){

                        if(currentBird!=null){

                            ObjectAnimator rotateAnimator = ObjectAnimator.ofFloat(imageView, "rotation", 360f, 0f);
                            rotateAnimator.setDuration(1000);

                            ObjectAnimator translateXAnimator = ObjectAnimator.ofFloat(imageView, "translationX", -angryBird.getInitialX());
                            translateXAnimator.setDuration(1000);

                            ObjectAnimator translateYAnimator = ObjectAnimator.ofFloat(imageView, "translationY", -angryBird.getInitialY());
                            translateYAnimator.setDuration(1000);

                            AnimatorSet animatorSet = new AnimatorSet();
                            animatorSet.playTogether(rotateAnimator, translateXAnimator, translateYAnimator);
                            animatorSet.start();

                        }

                        ObjectAnimator rotateAnimator = ObjectAnimator.ofFloat(imageView3, "rotation", 0f, 360f);
                        rotateAnimator.setDuration(1000);

                        ObjectAnimator translateXAnimator = ObjectAnimator.ofFloat(imageView3, "translationX", slingShotX);
                        translateXAnimator.setDuration(1000);

                        ObjectAnimator translateYAnimator = ObjectAnimator.ofFloat(imageView3, "translationY", slingShotY);
                        translateYAnimator.setDuration(1000);

                        AnimatorSet animatorSet = new AnimatorSet();
                        animatorSet.playTogether(rotateAnimator, translateXAnimator, translateYAnimator);
                        animatorSet.start();

                        currentBird = imageView3;
                    }

                    return true;
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

    private void setupDimentions(){

        if(orientation.equals("landscape")){
            FrameLayout slingshotFrameLayout = findViewById(R.id.slingshotFrame);
            slingshotFrameLayout.post(new Runnable() {
                @Override
                public void run() {
                    slingShotX = slingshotFrameLayout.getX() - (float) slingshotFrameLayout.getWidth() / 2 + 50;
                    slingShotY = -slingshotFrameLayout.getY() + 220;
                }
            });

            ImageView angryBirdView = findViewById(R.id.imageView2);
            angryBirdView.post(new Runnable() {
                @Override
                public void run() {
                    angryBird = new Bird(angryBirdView.getX(), angryBirdView.getY(), angryBirdView.getRotation());
                    Log.i("logx",String.valueOf(angryBird.getInitialX()));
                    Log.i("logx",String.valueOf(angryBird.getInitialY()));
                    Log.i("logx",String.valueOf(angryBird.getInitialRotation()));
                }
            });

            ImageView fastBirdView = findViewById(R.id.imageView3);
            fastBirdView.post(new Runnable() {
                @Override
                public void run() {
                    fastBird = new Bird(fastBirdView.getX(), fastBirdView.getY(), fastBirdView.getRotation());
                    Log.i("logx",String.valueOf(fastBird.getInitialX()));
                    Log.i("logx",String.valueOf(fastBird.getInitialY()));
                    Log.i("logx",String.valueOf(fastBird.getInitialRotation()));
                }
            });
        }

    }
}