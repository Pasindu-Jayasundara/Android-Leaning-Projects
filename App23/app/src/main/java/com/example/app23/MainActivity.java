package com.example.app23;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        VideoView videoView = findViewById(R.id.videoView);

        MediaController mediaController = new MediaController(MainActivity.this);
        mediaController.setMediaPlayer(videoView);
        mediaController.setAnchorView(videoView);

        videoView.setMediaController(mediaController);
        Uri uri = Uri.parse("https://sampletestfile.com/wp-content/uploads/2023/07/1MB-MP4.mp4");
        videoView.setVideoURI(uri);
        videoView.start();

    }
}