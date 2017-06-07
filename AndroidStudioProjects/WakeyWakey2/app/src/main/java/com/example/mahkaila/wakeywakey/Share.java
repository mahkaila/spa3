package com.example.mahkaila.wakeywakey;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareButton;

public class Share extends ActionBarActivity {

    TextView update_text;
    String time;
    String setTime = "yo";

    ShareButton shareButton;
    //image
    private Bitmap image;
    //counter
    private int counter = 0;
    String linkage = "https://67.media.tumblr.com/07ee69fa9d268acbd73e3368dd5b45b2/tumblr_oe5pn7TaGw1vnkxb0o1_1280.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);


        update_text = (TextView) findViewById(R.id.share_text);

        Button ok = (Button) findViewById(R.id.ok);

        update_text = (TextView) findViewById(R.id.share_text);

        Alarm alarm = new Alarm();

        time = alarm.getTime();

        set_share_text("Rise and shine! It's " + time + ". Let your friends know you're awake!");




        Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.notification_icon);

        shareButton = (ShareButton) findViewById(R.id.share_btn);

      ShareLinkContent content = new ShareLinkContent.Builder()
                .setContentUrl(Uri.parse(linkage))
                .setQuote("I woke up at " + time + "! Hooray!")
                .build();
                shareButton.setShareContent(content);


       shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent start_intent = new Intent(getApplicationContext(), Alarm.class);
                startActivity(start_intent);

            }
        });
    }


    private void set_share_text(String output) {
         update_text.setText(output);
    }
}

