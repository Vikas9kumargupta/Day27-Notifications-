package com.example.day27notifications

import android.Manifest
import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class MainActivity : AppCompatActivity() {

    val channel_Id = "channelId"
    val channel_name = "channelName"
    val notificationId = 0

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createNotificationChannel()

        val intent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this,0,intent, PendingIntent.FLAG_MUTABLE)

        val notification = NotificationCompat.Builder(this,channel_Id)
            .setContentTitle("30 days of App Dev Tutorial 27")
            .setContentText("Congratulations for showing up today")
            .setSmallIcon(R.drawable.baseline_message_24)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .build()

        val notificationManager  = NotificationManagerCompat.from(this)
        val btn = findViewById<Button>(R.id.button)

        btn.setOnClickListener{
            notificationManager.notify(notificationId , notification)
        }
    }

    private fun createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(channel_Id, channel_name, NotificationManager.IMPORTANCE_DEFAULT).apply {
                description = "This is my Notification Channel"
                lightColor = Color.GREEN
                enableLights(true)
            }

            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }
}