package com.example.mahkaila.wakeywakey;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;


import java.util.Calendar;

public class Alarm extends AppCompatActivity {

    AlarmManager alarm_manager;
    TimePicker alarm_timepicker;
    TextView update_text;
    Context context;
    PendingIntent pending_intent;
    String buttonState = "off";
    boolean alarmOn = false;
   // String alarmSetFor;


    final Calendar calendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        this.context = this;

        alarm_manager = (AlarmManager) getSystemService(ALARM_SERVICE);

        alarm_timepicker = (TimePicker) findViewById(R.id.timePicker);

        update_text = (TextView) findViewById(R.id.alarm_update);



        final Intent my_Intent = new Intent(this.context, Alarm_Reciever.class);



        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);



        Button alarm_on = (Button) findViewById(R.id.alarm_on);

        final Button alarm_off = (Button) findViewById(R.id.alarm_off);

        Button snooze = (Button) findViewById(R.id.snooze);

        Button awake = (Button) findViewById(R.id.awake);


        if (AccessToken.getCurrentAccessToken() == null) {
            goLoginScreen();
        }


        alarm_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                calendar.set(Calendar.HOUR_OF_DAY, alarm_timepicker.getHour());
                calendar.set(Calendar.MINUTE, alarm_timepicker.getMinute());

                int hour = alarm_timepicker.getHour();
                int minute = alarm_timepicker.getMinute();

                String hour_string = String.valueOf(hour);
                String minute_string = String.valueOf(minute);


                if (hour > 12) {
                    hour_string = String.valueOf(hour - 12);
                }

                if (minute < 10) {
                    minute_string = "0" + String.valueOf(minute);
                }

               // buttonState = "woo!";


                set_alarm_text("Alarm set to " + hour_string + ":" + minute_string);

                String alarmSetFor = (hour_string + ":" + minute_string);

                Log.e("We are in!", alarmSetFor);

                if(alarmOn == false) {



                    Intent intent = new Intent(Alarm.this, Goodnight.class);
                    intent.putExtra("alarmSet", alarmSetFor);
                    startActivity(intent);
                }
                my_Intent.putExtra("extra", "alarm on");
              //  my_Intent.putExtra("alarmSet", alarmSetFor);

                pending_intent = PendingIntent.getBroadcast(Alarm.this, 0, my_Intent, PendingIntent.FLAG_UPDATE_CURRENT);

                alarm_manager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pending_intent);

                alarmOn = true;
                //startNewActivity();


            }
        });

        alarm_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //set_alarm_text("Alarm off!");

                set_alarm_text("Alarm reset!");

                //alarm_manager.cancel(pending_intent);

                my_Intent.putExtra("extra", "alarm off");

                sendBroadcast(my_Intent);

                alarmOn = false;
                //startNewActivity();
            }
        });

        snooze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                set_alarm_text("snooze ");

                //alarm_manager.cancel(pending_intent);

                my_Intent.putExtra("extra", "snooze");

                sendBroadcast(my_Intent);

                my_Intent.putExtra("extra", "snooze1");

                int seconds = calendar.get(Calendar.SECOND);
                int hours = calendar.get(Calendar.HOUR);

                calendar.set(Calendar.HOUR_OF_DAY, hours);
                calendar.set(Calendar.SECOND, seconds);

                pending_intent = PendingIntent.getBroadcast(Alarm.this, 0, my_Intent, PendingIntent.FLAG_UPDATE_CURRENT);
                alarm_manager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis()+60000, pending_intent);

                alarmOn = false;
            }
        });

        awake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                set_alarm_text(buttonState);

                //Log.e("We are in!", );
              //  alarm_manager.cancel(pending_intent);

                my_Intent.putExtra("extra", "awake");

                my_Intent.putExtra("Time", "The time is");

                sendBroadcast(my_Intent);

                alarmOn = false;
            }
        });

    }

    public void snooze() {

        Log.e("We are in!", "Snooze!");
        buttonState = "snooze1";



    }

    public void awake() {
        //buttonState = s;
        Log.e("We are in!", "Awake!");

        //Log.e("We are in! ", s);
    }
    private void goLoginScreen() {
        Intent intent = new Intent(this, FBLogin.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }
    private void startNewActivity() {
        Intent start_intent = new Intent(getApplicationContext(), Awake.class);
        start_intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(start_intent);
    }

    private void logout(View view){
        LoginManager.getInstance().logOut();
        goLoginScreen();
    }

    public String getTime(){
        int minute = calendar.get(Calendar.MINUTE);
        int hour = calendar.get(Calendar.HOUR);

        String hour_string = String.valueOf(hour);
        String minute_string = String.valueOf(minute);


        if (hour > 12) {
            hour_string = String.valueOf(hour - 12);
        }

        if (minute < 10) {
            minute_string = "0" + String.valueOf(minute);
        }

        return (hour_string + ":" + minute_string);
    }

    public String setTime(){
        int hour = alarm_timepicker.getHour();
        int minute = alarm_timepicker.getMinute();

        String hour_string = String.valueOf(hour);
        String minute_string = String.valueOf(minute);


        if (hour > 12) {
            hour_string = String.valueOf(hour - 12);
        }

        if (minute < 10) {
            minute_string = "0" + String.valueOf(minute);
        }

        return (hour_string + ":" + minute_string);

    }

    private void set_alarm_text(String output) {
        update_text.setText(output);
    }


}
