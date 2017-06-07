package com.example.mahkaila.wakeywakey;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by mahkaila on 25/09/16.
 */

public class OpenActivity extends AppCompatActivity {

    public void open(){

        Intent start_intent = new Intent(getApplicationContext(), Awake.class);
        startActivity(start_intent);

    }
}
