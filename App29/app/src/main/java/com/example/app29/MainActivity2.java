package com.example.app29;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.JointType;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.RoundCap;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        SupportMapFragment supportMapFragment = new SupportMapFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.frameLayout2,supportMapFragment).commit();

        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {

                LatLng latLng = new LatLng(6.940485,79.852811);
                googleMap.animateCamera(
                        CameraUpdateFactory.newCameraPosition(
                                new CameraPosition.Builder()
                                        .target(latLng)
                                        .zoom(18)
                                        .build()
                        )
                );

                LatLng latLng1 = new LatLng(6.955736,79.860622);
                LatLng latLng2 = new LatLng(6.944489,79.855215);
                LatLng latLng3 = new LatLng(6.940485,79.852811);

                ArrayList<LatLng> lines = new ArrayList<>();
                lines.add(latLng1);
                lines.add(latLng2);
                lines.add(latLng3);

                googleMap.addPolyline(
                        new PolylineOptions()
                                .addAll(lines)
                                .width(20)
                                .color(getColor(R.color.black))
                                .startCap(new RoundCap())
                                .endCap(new RoundCap())
                                .jointType(JointType.ROUND)
                );

                googleMap.addMarker(
                        new MarkerOptions()
                                .title("Rider")
                                .position(latLng3)
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.download2))
                ).showInfoWindow();

            }
        });

    }
}