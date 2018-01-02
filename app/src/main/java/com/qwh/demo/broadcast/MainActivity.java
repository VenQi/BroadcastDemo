package com.qwh.demo.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    NetWorkBroadcast netWorkBroadcast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IntentFilter filter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        netWorkBroadcast = new NetWorkBroadcast();
        registerReceiver(netWorkBroadcast,filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(netWorkBroadcast);
    }

    class NetWorkBroadcast extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info  = manager.getActiveNetworkInfo();
            if (null == info){
                Toast.makeText(context,"net work is unavailiable",Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(context,"net work is availiable ,net type is :"+info.getTypeName()+"subtype"+info.getSubtypeName(),Toast.LENGTH_LONG).show();
            }

        }
    }
}
