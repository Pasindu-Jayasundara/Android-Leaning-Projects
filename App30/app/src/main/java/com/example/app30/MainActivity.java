package com.example.app30;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

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

        SupportMapFragment supportMapFragment = new SupportMapFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.frameLayout,supportMapFragment).commit();

        Button btn1 = findViewById(R.id.button2);
        btn1.setEnabled(false);
        Button btn2 = findViewById(R.id.button);
        btn2.setEnabled(false);

        ArrayList<LatLng> latLngArrayList = new ArrayList<>();

        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {

                LatLng latLng = new LatLng(12.4588455,5.124578);
                googleMap.animateCamera(
                        CameraUpdateFactory.newCameraPosition(
                                new CameraPosition.Builder()
                                        .target(latLng)
                                        .zoom(15)
                                        .build()
                        )
                );

                // add place btn
                btn1.setEnabled(true);
                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        btn1.setEnabled(true);
                        btn2.setEnabled(false);

                        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener(){
                            @Override
                            public void onMapClick(@NonNull LatLng latLng){

                                googleMap.addMarker(
                                        new MarkerOptions()
                                                .title("Hello")
                                                .position(latLng)
                                                .icon(BitmapDiscriptorFactory.fromResource(R.drawable.placeholder_pin))
                                ).showInfoWindow();

                                latLngArrayList.add(latLng);

                            }
                        });

                    }
                });

                // save btn
                btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        btn1.setEnabled(false);
                        btn2.setEnabled(true);

                        googleMap.addPolyline(
                                new PolylineOptions()
                                        .addAll(latLngArrayList)
                                        .color(getColor(R.color.color1))
                                        .width(8)
                                        .startCap(new RoundCap())
                                        .endCap(new RoundCap())
                                        .jointType(JointType.ROUND)
                        );

                    }
                });

            }

        });

    }
}