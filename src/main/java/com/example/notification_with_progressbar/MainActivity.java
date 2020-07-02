package com.example.notification_with_progressbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public static final String CHENNEL_ID = "Personal_Notifications";
    public static final int NOTIFICATION_ID = 001;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Get_Notification(View view) {

        CreateNotificationChennel();

        final NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHENNEL_ID);
        builder.setSmallIcon(R.drawable.ic_cloud_download);
        builder.setContentTitle("Downloading Notification");
        builder.setContentText("This is downloading notification.");
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);

        final int maxProgress = 100;
        int currentProgress = 0;

        builder.setProgress(0, 0, true);

        final NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(NOTIFICATION_ID, builder.build());

         Thread thread = new Thread(){



             @Override
             public void run() {

                 int count = 0;

                 try{

                 while (count < 100) {


                     count++;
                     sleep(1000);
                    // builder.setProgress(maxProgress, count, false);
                    // notificationManagerCompat.notify(NOTIFICATION_ID, builder.build());

                 }

                     builder.setContentText("Downloading Complete.");
                     builder.setProgress(0, 0, false);
                     notificationManagerCompat.notify(NOTIFICATION_ID, builder.build());

                 }

                     catch (InterruptedException e){}





             }
         };

         thread.start();


    }

    private void CreateNotificationChennel() {

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            CharSequence name = "Personal Notification";
            String description = "This is personal notification";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel notificationChannel = new NotificationChannel(CHENNEL_ID, name, importance);
            notificationChannel.setDescription(description);

            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
        }
        }
}