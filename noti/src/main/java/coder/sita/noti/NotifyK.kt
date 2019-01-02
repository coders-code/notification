package coder.sita.noti

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.support.v4.app.NotificationCompat

object NotifyK {
    private var notifManager: NotificationManager? = null

    fun show(c: CharSequence, mContext: Context, title: String, message: String, notiFyID: Int, cls: Class<*>) {
        val icon = R.drawable.ic_
        if (notifManager == null)
            notifManager = mContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val mBuilder = NotificationCompat.Builder(mContext)
        val notificationIntent = Intent(mContext, cls)
        val contentIntent = PendingIntent.getActivity(mContext, 0, notificationIntent, 0)
        val notification: Notification
        val CHANNEL_ID = "Coder"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_HIGH
            val mChannel = NotificationChannel(CHANNEL_ID, c, importance)
            notificationIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            notification = mBuilder.setContentTitle(title).setSmallIcon(icon).setDefaults(Notification.DEFAULT_ALL).setAutoCancel(true).setStyle(NotificationCompat.BigTextStyle().bigText(message)).setContentIntent(contentIntent).setChannelId(CHANNEL_ID).setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)).setPriority(Notification.PRIORITY_HIGH).setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000)).setContentText(message).build()
            notifManager!!.createNotificationChannel(mChannel)
            notifManager!!.notify(notiFyID, notification)
        } else {
            notification = mBuilder.setSmallIcon(icon).setTicker(title).setWhen(0).setAutoCancel(true).setContentTitle(title).setStyle(NotificationCompat.BigTextStyle().bigText(message)).setContentIntent(contentIntent).setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)).setPriority(Notification.PRIORITY_HIGH).setContentText(message).build()
            notifManager!!.notify(notiFyID, notification)
        }
    }
}
