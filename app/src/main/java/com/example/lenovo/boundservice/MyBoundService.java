package com.example.lenovo.boundservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

import java.util.Random;

public class MyBoundService extends Service {

    private final Binder mbinder = new LocalBinder();
    private final Random mgenetar = new Random();

    public class LocalBinder extends Binder {
        MyBoundService getService() {
            return MyBoundService.this;
        }
    }

    public int getGenerator() {
        return mgenetar.nextInt(100);
    }

    @Override
    public IBinder onBind(Intent intent) {
        Toast.makeText(this, "service onBind", Toast.LENGTH_SHORT).show();
        return mbinder;
    }

    @Override
    public void onCreate() {
        Toast.makeText(this, "service onCreate", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStart(Intent i, int startid) {
        Toast.makeText(this, "service onStart", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "service onDestroy", Toast.LENGTH_SHORT).show();
    }

}
