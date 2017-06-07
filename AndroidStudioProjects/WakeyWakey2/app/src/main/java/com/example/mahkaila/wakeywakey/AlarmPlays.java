package com.example.mahkaila.wakeywakey;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Calendar;

public class AlarmPlays extends AppCompatActivity {

    Context context;
    AlarmManager alarm_manager;
    PendingIntent pending_intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_plays);

        alarm_manager = (AlarmManager)getSystemService(ALARM_SERVICE);
        final Calendar calendar = Calendar.getInstance();

        final Intent my_Intent = new Intent(this.context, Alarm_Reciever.class);

        this.context = this;

        Button rightyo = (Button)findViewById(R.id.rightyo);



        rightyo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                my_Intent.putExtra("extra", "rightyo");


                int seconds = calendar.get(Calendar.SECOND);
                int hours = calendar.get(Calendar.HOUR);

                calendar.set(Calendar.HOUR_OF_DAY, hours);
                calendar.set(Calendar.SECOND, seconds);


                pending_intent = PendingIntent.getBroadcast(AlarmPlays.this, 0, my_Intent, PendingIntent.FLAG_UPDATE_CURRENT);

                alarm_manager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis() + 30000, pending_intent);

                //Intent start_intent = new Intent(getApplicationContext(), Alarm.class);
                //startActivity(start_intent);

            }
        });
    }
}
