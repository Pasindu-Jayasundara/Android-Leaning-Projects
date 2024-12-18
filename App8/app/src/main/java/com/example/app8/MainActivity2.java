package com.example.app8;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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

        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ArrayList<String> permissionList = new ArrayList<>();

                if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                    Log.i("MainActivity 2","Already has write external storage permission");
                }else{
                    permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
                }

                if(checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED){
                    Log.i("MainActivity 2","Already has send sms permission");
                }else{
                    permissionList.add(Manifest.permission.SEND_SMS);
                }

                if(checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
                    Log.i("MainActivity 2","Already has camera permission");
                }else{
                    permissionList.add(Manifest.permission.CAMERA);
                }

                if(!permissionList.isEmpty()){

                    // String permissionArray[] = (String[]) permissionList.toArray();

                    String permissionArray[] = permissionList.toArray(new String[permissionList.size()]);
                    requestPermissions(permissionArray,200);
                }else{
                    Log.i("MainActivity 2","Already has All the permissions");
                }

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == 200){

            for (int i = 0; i < permissions.length; i++) {
                String permission = permissions[i];

                if(grantResults[i] == PackageManager.PERMISSION_GRANTED){
                    Log.i("Manifest 2",permission+" permission granted");

                    if(permission.equals(Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                        //
                    }else if(permission.equals(Manifest.permission.SEND_SMS)){
                        //
                    }else if(permission.equals(Manifest.permission.CAMERA)){
                        //
                    }
                }else{
                    Log.i("Manifest 2",permission+" permission denied");
                }

            }

        }
    }
}