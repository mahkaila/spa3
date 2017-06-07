package com.example.mahkaila.wakeywakey;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by mahkaila on 22/09/16.
 */
public class Awake_Reciever extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent){

        Log.e("We in awake receiver!", "Yay!");


        String get_your_string = intent.getExtras().getString("extra");

        Log.e("What is the key? ", get_your_string);


        Intent service_intent = new Intent(context, RingtonePlayingService.class);

        service_intent.putExtra("extra", get_your_string);


        context.startService(service_intent);

    }

}