package org.techtown.noti2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.techtown.noti2.R;

public class MainActivity extends AppCompatActivity {
    NotificationManager manager;

    private static String CHANNEL_ID = "channel1";
    private static String CHANNEL_NAME = "channel1";

    private static String CHANNEL_ID2 = "channel2";
    private static String CHANNEL_NAME2 = "channel2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNoti1();
            }
        });

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNoti2();
            }
        });
    }
    public void showNoti1(){
        manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationCompat.Builder builder = null; // 예전 버전 단말까지 기능 지원

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (manager.getNotificationChannel(CHANNEL_ID) == null) {
                manager.createNotificationChannel(new NotificationChannel(
                        CHANNEL_ID,
                        CHANNEL_NAME,
                        NotificationManager.IMPORTANCE_DEFAULT
                ));
            }

            builder = new NotificationCompat.Builder(this,CHANNEL_ID);
        } else {
            builder = new NotificationCompat.Builder(this);
        }

        builder.setContentTitle("간단 알림");
        builder.setContentText("간단 알림 메시지입니다.");
        builder.setSmallIcon(android.R.drawable.ic_menu_view);


        Notification noti = builder.build();
        manager.notify(1, noti);
    }

    public void showNoti2() {
        manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationCompat.Builder builder = null;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if(manager.getNotificationChannel(CHANNEL_ID2) == null) {
                manager.createNotificationChannel(new NotificationChannel(
                        CHANNEL_ID2,
                        CHANNEL_NAME2,
                        NotificationManager.IMPORTANCE_DEFAULT
                ));

                builder = new NotificationCompat.Builder(this,CHANNEL_ID2);
            }
        }else {
            builder = new NotificationCompat.Builder(this);
        }

        Intent intent = new Intent(this,MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,101, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentTitle("간단 알림");
        builder.setContentText("간단 알림 메시지입니다.");
        builder.setSmallIcon(android.R.drawable.ic_menu_view);
        builder.setAutoCancel(true);
        builder.setContentIntent(pendingIntent);

        Notification noti = builder.build();

        manager.notify(2,noti);
    }
}