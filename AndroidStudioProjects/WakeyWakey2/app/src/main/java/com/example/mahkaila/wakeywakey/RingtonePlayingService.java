package com.example.mahkaila.wakeywakey;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;


import java.security.Provider;
import java.util.List;
import java.util.Map;

/**
 * Created by mahkaila on 22/09/16.
 */
public class RingtonePlayingService extends Service  {

    MediaPlayer media_song;
    int startId;
    boolean isRunning;
    boolean beenClicked = false;
    String time;
    String setTime;
    AlarmManager alarm_manager;
    PendingIntent pending_intent;




    private PopupWindow popupWindow;
    private LayoutInflater layoutInflater;
    private RelativeLayout relativeLayout;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("LocalService", "Received start id " + startId + ": " + intent);


        String state = intent.getExtras().getString("extra");
        time = intent.getExtras().getString("Time");
     //   setTime = intent.getExtras().getString("alarmSet");



        assert  state != null;
        switch (state) {
            case "alarm on":
                startId = 1;
                break;
            case "rightyo":
                startId = 0;
                break;
            case "snooze":
                startId = 2;
                break;
            case "awake":
                startId = 3;
                break;
            case "snooze1":
                startId = 4;
                break;
            case "alarm off":
                startId = 0;
                Log.e("Start ID is ", state);
                break;
            default:
                startId = 0;
                break;
        }

        alarm_manager = (AlarmManager) getSystemService(ALARM_SERVICE);

        if(!this.isRunning && startId == 1){
        //alarm on
            Log.e("There is no music ", "and you want start" );

            media_song = MediaPlayer.create(this, R.raw.birds);
            media_song.start();

            this.isRunning = true;
            this.startId = 0;


            NotificationManager notify_manager = (NotificationManager)
                    getSystemService(NOTIFICATION_SERVICE);

            Intent intent_Alarm = new Intent(this.getApplicationContext(), Alarm.class);

            PendingIntent pending_intent_Alarm = PendingIntent.getActivity(this, 0, intent_Alarm, 0);

            Notification notification_popup = new Notification.Builder(this)
                    .setContentTitle("The alarm is going off!")
                    .setContentText("Click me!")
                    .setContentIntent(pending_intent_Alarm)
                    .setSmallIcon(R.drawable.notification_icon)
                    .setAutoCancel(true)
                    .build();

            notify_manager.notify(0, notification_popup);

        }else if(!this.isRunning && startId == 4 && this.beenClicked){
        //snooze1
            Log.e("There is no music ", "and you want start" );

            media_song = MediaPlayer.create(this, R.raw.birds);
            media_song.start();

            this.isRunning = true;
            this.startId = 0;


            NotificationManager notify_manager = (NotificationManager)
                    getSystemService(NOTIFICATION_SERVICE);

            Intent intent_Alarm = new Intent(this.getApplicationContext(), Alarm.class);

            PendingIntent pending_intent_Alarm = PendingIntent.getActivity(this, 0, intent_Alarm, 0);

            Notification notification_popup = new Notification.Builder(this)
                    .setContentTitle("Snooze time is over!")
                    .setContentText("Click me!")
                    .setContentIntent(pending_intent_Alarm)
                    .setSmallIcon(R.drawable.notification_icon)
                    .setAutoCancel(true)
                    .build();

            notify_manager.notify(0, notification_popup);

            this.beenClicked = false;


        }else if(this.isRunning && startId == 2) {
            //snooze
            Log.e("There is music ", "and you want end" );

            alarm_manager.cancel(pending_intent);

            media_song.stop();
            media_song.reset();

            this.isRunning = false;
            this.startId = 0;

            startSnooze();

            this.beenClicked = true;

        }

        else if(this.isRunning && startId == 0 ) {
            //cancel alarm
            Log.e("There is music ", "and you want end" );

            GoBack();

            this.isRunning = true;
            this.startId = 0;

        }else if(!this.isRunning && startId == 0){
            //cancel alarm
            Log.e("There is no music ", "and you want end" );

            alarm_manager.cancel(pending_intent);

            this.isRunning = false;
            this.startId = 0;

        } else if(!this.isRunning && startId == 2){
            //snooze
            Log.e("There is no music ", "and you want end" );

            this.isRunning = false;
            this.startId = 0;

            this.beenClicked = false;

        }else if(this.isRunning && startId == 1) {
            //alarm on
            Log.e("There is music ", "and you want start" );

            this.isRunning = true;
            this.startId = 1;

        }else if(!this.isRunning && startId == 3){
            //awake
            Log.e("There is no music ", "and you want end" );

            this.isRunning = false;
            this.startId = 0;

        }else if(this.isRunning && startId == 3){
            //awake
            Log.e("There is music ", "and you want end" );

            alarm_manager.cancel(pending_intent);

            media_song.stop();
            media_song.reset();


            this.isRunning = false;
            this.startId = 0;

            startSelfieActivity();

        } else {
            Log.e("else ", "somehow you reached this.");

        }

        return START_NOT_STICKY;
    }



    private void GoBack() {
        Intent start_intent = new Intent(getApplicationContext(), GoBack.class);
        start_intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(start_intent);
    }

    private void startSnooze() {
        Intent start_intent = new Intent(getApplicationContext(), Snooze.class);
        start_intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(start_intent);
    }

    private void startNewActivity() {
        Intent start_intent = new Intent(getApplicationContext(), Awake.class);
        start_intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(start_intent);
    }

    private void startSelfieActivity() {
        Intent start_intent = new Intent(getApplicationContext(), Selfie.class);
        start_intent.putExtra("Time", time);
        start_intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(start_intent);
    }

    @Override
    public void onDestroy() {

        // Tell the user we stopped.
        Toast.makeText(this, "On destroy called", Toast.LENGTH_SHORT).show();
    }


}
