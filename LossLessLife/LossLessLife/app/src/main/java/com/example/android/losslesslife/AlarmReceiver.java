package com.example.android.losslesslife;

/**
 * Created by usman on 12/9/2018.
 */
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.widget.Toast;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent)
    {

       // String alarm_toast = getResources().getString(R.string.something);

        Toast.makeText(context,"You have to do something",Toast.LENGTH_LONG).show();

        //Intent intent2 = getIntent();

        //String fName = intent.getStringExtra("firstName");


        Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if (alarmUri == null)
        {
            alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }
        final Ringtone ringtone = RingtoneManager.getRingtone(context, alarmUri);
        ringtone.play();
      //  final Handler handler = new Handler();
       // handler.postDelayed(new Runnable() {
         //   @Override
        //    public void run() {
                //Do something after 100ms

          //  }
        //}, 5000);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                ringtone.stop();
                // yourMethod();
            }
        }, 15000);   //5 seconds
    }
}
