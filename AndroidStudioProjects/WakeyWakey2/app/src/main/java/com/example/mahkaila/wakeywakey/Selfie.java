package com.example.mahkaila.wakeywakey;

import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Selfie extends AppCompatActivity {

    /*Context context;
    String time;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("LocalService", "Received start id " + startId + ": " + intent);

        time = intent.getExtras().getString("Time");

        return 0;

        }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selfie);

        //this.context = this;

        Button selfie = (Button) findViewById(R.id.selfieButton);



        selfie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent start_intent = new Intent(getApplicationContext(), Share.class);
               // start_intent.putExtra("Time", time);
                startActivity(start_intent);

            }
        });
    }

    /*public String getTime(){

        return time;
    }*/
}
