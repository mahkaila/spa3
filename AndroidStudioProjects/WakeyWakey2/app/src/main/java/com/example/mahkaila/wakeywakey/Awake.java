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

public class Awake extends Alarm {

    Context context;
    AlarmManager alarm_manager;
    PendingIntent pending_intent;
    Alarm alarm;
    Boolean snoozed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_awake);

        alarm_manager = (AlarmManager)getSystemService(ALARM_SERVICE);
        //final Calendar calendar = Calendar.getInstance();

      //  final Intent the_Intent = new Intent(this.context, Awake_Reciever.class);

        this.context = this;

        alarm = new Alarm();




        Button awake = (Button)findViewById(R.id.awake);

        Button snooze = (Button)findViewById(R.id.snooze);

        awake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // alarm.awake();
              //the_Intent.putExtra("extra", "awake");
                Intent start_intent = new Intent(getApplicationContext(), Selfie.class);
                startActivity(start_intent);
            }
        });

        snooze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //the_Intent.putExtra("extra", "alarm off");
                //
                // alarm.getButtonState();
                // alarm.tester();
                //alarm.snooze();
                // snoozed = true;

                // Intent the_Intent = new Intent(getApplicationContext(),Alarm_Reciever.class);
                // the_Intent.putExtra("extra", "snooze");
                //startActivity(the_Intent);


                Intent start_intent = new Intent(getApplicationContext(), Alarm.class);
                //start_intent.putExtra("hasSnoozed", "snooze");
                startActivity(start_intent);

            }
        });
    }

    public boolean snoozedIsClicked(){
        if (snoozed == true){
            return true;
        }else{
            return false;
        }
    }
}
