package com.example.myclicktest

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import java.util.*

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(p0: RemoteMessage) {
        super.onMessageReceived(p0)
        val data = p0.notification
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val builder = NotificationCompat.Builder(this, "channelId")
        builder.setContentTitle(data?.title)
        builder.setContentText(data?.body)
        builder.setSmallIcon(R.drawable.ic_launcher_foreground)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "channelId"
            val descrp = "Channel desc"
            val imoportnace = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("channelId", name, imoportnace).apply { description = descrp }
            notificationManager.createNotificationChannel(channel)
        }
        notificationManager.notify(1, builder.build())

        Log.d("TAG", "onMessageReceived Title : ${data?.title}")
        Log.d("TAG", "onMessageReceived Body : ${data?.body}")
    }


    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
        Log.d("TAG", "onNewToken $p0")
    }
}