package com.example.mahkaila.wakeywakey;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareButton;

public class Goodnight extends ActionBarActivity {

    TextView update_text;

    ShareButton shareButton;
    //image
    private Bitmap image;
    //counter
    private int counter = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goodnight);

        Bundle b = new Bundle();
        b = getIntent().getExtras();
        String alarmSet = b.getString("alarmSet");

        update_text = (TextView) findViewById(R.id.time);

        Button ok = (Button) findViewById(R.id.ok);

        set_share_text("The alarm has bee set for " + alarmSet + ". Click below to share with your facebook friends!");

        Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.notification_icon);

        shareButton = (ShareButton) findViewById(R.id.share_btn);

       /* SharePhoto photo = new SharePhoto.Builder()
                .setBitmap(image)
                .setCaption("Goodnight friends! Hopefully I'll see you when I wake up at " + alarmSet + "! Haha lol!")
                .build();
        SharePhotoContent content = new SharePhotoContent.Builder()
                .setContentUrl(Uri.parse("https://developers.facebook.com"))
                .addPhoto(photo)
                .build();
                 shareButton.setShareContent(content);*/

       shareButton = (ShareButton) findViewById(R.id.share_btn);


        ShareButton shareButton= (ShareButton) findViewById(R.id.share_btn);
        ShareLinkContent content = new ShareLinkContent.Builder()
                .setContentUrl(Uri.parse("https://67.media.tumblr.com/b17ca2f200212fcc638e2e0d3903f721/tumblr_oe5pqkWfkk1vnkxb0o1_1280.png"))
                .setQuote("Goodnight friends! Hopefully I'll see you when I wake up at " + alarmSet + "! Haha lol!")
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
