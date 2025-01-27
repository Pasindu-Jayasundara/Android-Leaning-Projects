package com.example.app29;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.app29.connection.Database;
import com.example.app29.dto.LocationDTO;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

public class MainActivity3 extends AppCompatActivity {

    private Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        this.db = new Database(this);
        insertLocation();

        SupportMapFragment supportMapFragment = new SupportMapFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.frameLayout3,supportMapFragment).commit();


        LocationDTO locationById = db.getLocationsById(1);

        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {

                googleMap.animateCamera(
                        CameraUpdateFactory.newCameraPosition(
                                new CameraPosition.Builder()
                                        .target(new LatLng(locationById.getLatitude(),locationById.getLongtitude()))
                                        .zoom(10)
                                        .build()
                        )
                );


            }
        });
    }

    public void insertLocation(){
        LocationDTO locationDTO = new LocationDTO("Lotus Tower",65656556,5454545);
        boolean added = db.addLocation(locationDTO);
        if(added){
            Toast.makeText(this,"Location Added",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,"Location Added Failed",Toast.LENGTH_LONG).show();
        }
    }

}