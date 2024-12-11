package com.example.app41;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class X extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

//        Log.d("Broadcast","Hello");
//        Toast.makeText(context, "Hello", Toast.LENGTH_SHORT).show();

        if(intent.getBooleanExtra("state",false)){
            Log.d("Broadcast","Airplane Mode Enabled");

            context.startActivity(new Intent(context,BroadcastActivity.class));
        }

    }
}
