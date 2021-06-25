package com.al.wallpaperservice;

import android.app.Service;
import android.app.WallpaperManager;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;

import androidx.annotation.Nullable;

import java.util.Timer;
import java.util.TimerTask;

public class WallpaperService extends Service {
   private Handler mHandler = new Handler();

   Integer wallpapers[]={R.drawable.wallpaper,R.drawable.wallpaper2,R.drawable.wallpaper3};
   Timer timer;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        if(timer!=null){
            timer.cancel();
        }else {
            timer=new Timer();
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

       // String time = intent.getStringExtra("time");
        timer.schedule(new Task(),10,Integer.parseInt(intent.getStringExtra("time")));
        return START_STICKY;
    }


    int index=0;

    class Task extends TimerTask{

        @Override
        public void run() {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    setWallPaperImages();
                }
            });

        }
    }

    private void setWallPaperImages() {

        WallpaperManager wallpaperManager=WallpaperManager.getInstance(this);

        try{
            if(index!=wallpapers.length){

                wallpaperManager.setResource(wallpapers[index++]);

            }else {
                index=0;
            }

        }catch (Exception e){

        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();

        if(timer!=null){
            timer.cancel();
        }
    }


}