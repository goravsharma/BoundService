package com.example.lenovo.boundservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    MyBoundService mService;
    Boolean bound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this, "MainActivity OnCreate", Toast.LENGTH_SHORT).show();
    }

    public void bindtMyService(View view) {
        Intent i = new Intent(this, MyBoundService.class);
        bindService(i, mconn, Context.BIND_AUTO_CREATE);
    }

    public void generateRandomNo(View view) {
        if (! bound)
            Toast.makeText(this, "service not bounded", Toast.LENGTH_SHORT).show();
        if (bound) {
            int num = mService.getGenerator();
            Toast.makeText(this,"number: " + num, Toast.LENGTH_SHORT).show();
        }
    }

    public void unBindMyService(View view) {
        if (! bound)
            Toast.makeText(this, "service not bounded", Toast.LENGTH_SHORT).show();
        if (bound) {
            unbindService(mconn);
            bound = false;
        }
    }

    private ServiceConnection mconn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyBoundService.LocalBinder binder = (MyBoundService.LocalBinder) service;
            mService = binder.getService();
            bound = true;

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            bound = false;
        }
    };

}
