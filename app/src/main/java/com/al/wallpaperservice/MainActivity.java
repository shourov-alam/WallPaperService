package com.al.wallpaperservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {

    RadioButton second5,second10,second30;
    Button start,stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        second5=findViewById(R.id.second_5);
        second10=findViewById(R.id.second_10);
        second30=findViewById(R.id.second_30);

        start=findViewById(R.id.start);
        stop=findViewById(R.id.stop);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String a="0";
                if(second5.isChecked()){
                    a="5000";
                }
                if(second10.isChecked()){
                    a="10000";
                }
                if(second30.isChecked()){
                    a="30000";
                }

                Intent intent =new Intent(getApplicationContext(),WallpaperService.class);
                intent.putExtra("time",a);
                startService(intent);
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(getApplicationContext(),WallpaperService.class));
            }
        });




    }
}