package com.example.android.losslesslife;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.app.PendingIntent;
import android.content.Intent;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class AddReminder extends AppCompatActivity implements  View.OnClickListener{
    EditText daily0;
    EditText weekly0;
    EditText monthly0;
    EditText yearly0;
    EditText message0;

    //RadioButton notification0;
    //RadioButton alarm0;
    NumberPicker np;
    Button ok ;
    Spinner days;
    Button button_for_reminder;
    TimePicker alarmTimePicker;

    PendingIntent pendingIntent;
    AlarmManager alarmManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);
        daily0 = findViewById(R.id.daily);
        weekly0 = findViewById(R.id.weekly);
        monthly0 = findViewById(R.id.monthly);
        yearly0 = findViewById(R.id.yearly);
        message0 = findViewById(R.id.message);
       // notification0 = findViewById(R.id.notification);
       // alarm0 = findViewById(R.id.alarm);

        alarmTimePicker = (TimePicker) findViewById(R.id.simpleTimePicker);
        button_for_reminder = (Button) findViewById(R.id.reminder_button) ;
        alarmTimePicker.setIs24HourView(true);
        button_for_reminder.setOnClickListener(this);


        //daily0.setOnClickListener(this);
        //weekly0.setOnClickListener(this);
        //monthly0.setOnClickListener(this);
        //yearly0.setOnClickListener(this);
        //notification0.setOnClickListener(this);
        //alarm0.setOnClickListener(this);
        ok = (Button) findViewById(R.id.ok);
        np = (NumberPicker) findViewById(R.id.numberPicker1);
        days = (Spinner) findViewById(R.id.days);
        ok.setOnClickListener(this);
        np.setMaxValue(28);
        np.setMinValue(1);



            }



    @Override
    public void onClick(View view) {

        daily0 =  (EditText) findViewById(R.id.daily);
        monthly0 =  (EditText) findViewById(R.id.monthly);
        yearly0 =  (EditText) findViewById(R.id.yearly);
        message0 =  (EditText) findViewById(R.id.message);
        //weekly0 =  (EditText) findViewById(R.id.weekly);
        int idaily=Integer.parseInt( daily0.getText().toString());
        int imonthly=Integer.parseInt( monthly0.getText().toString());
        int iyearly=Integer.parseInt( yearly0.getText().toString());
        //int iweekly=Integer.parseInt( weekly0.getText().toString());

                alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                {
                    long time;
                    Toast.makeText(AddReminder.this, "ALARM ON", Toast.LENGTH_SHORT).show();
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Calendar.YEAR, iyearly);
                    //objCalendar.set(Calendar.YEAR, objCalendar.get(Calendar.YEAR));
                    calendar.set(Calendar.MONTH, imonthly);
                    calendar.set(Calendar.DAY_OF_MONTH, idaily);
                    calendar.set(Calendar.HOUR_OF_DAY, alarmTimePicker.getCurrentHour());
                    calendar.set(Calendar.MINUTE,alarmTimePicker.getCurrentMinute());
                    Intent intent = new Intent(this, AlarmReceiver.class);
                    intent.putExtra("firstName", "Value you want to pass");
                    // send the message to firebase here
                    pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);

                    int sec = 30;
                    alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
                }


    }
}
