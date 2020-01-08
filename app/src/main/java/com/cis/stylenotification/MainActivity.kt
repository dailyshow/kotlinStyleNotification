package com.cis.stylenotification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // notification에 이미지를 출력해본다.
        notiImgBtn.setOnClickListener { view ->
            val builder = getNotification("style", "style notification")
            builder.setContentTitle("기본 노티 타이틀")
            builder.setContentText("기본 노티 내용")
            builder.setSmallIcon(android.R.drawable.ic_dialog_info)

            val bigStyle = NotificationCompat.BigPictureStyle(builder)
            val img = BitmapFactory.decodeResource(resources, R.drawable.img_android)

            bigStyle.bigPicture(img)
            bigStyle.setBigContentTitle("이미지 노티 타이틀")
            bigStyle.setSummaryText("이미지 노티 summary text!!!!")

            val notification = builder.build()
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            manager.notify(10, notification)
        }

        // notification에 많은 글자를 출력해본다.
        notiManyTxtBtn.setOnClickListener { view ->
            val builder = getNotification("style", "style notification")
            builder.setContentTitle("기본 노티 타이틀")
            builder.setContentText("기본 노티 내용")
            builder.setSmallIcon(android.R.drawable.ic_dialog_info)

            val manyTxt = NotificationCompat.BigTextStyle(builder)
            manyTxt.setBigContentTitle("많은 내용 노티 타이틀")
            manyTxt.setSummaryText("많은 내용 노티 summary text!!")
            manyTxt.bigText("가나다라마바사~~가나다라마바사~~가나다라마바사~~가나다라마바사~~가나다라마바사~~가나다라마바사~~")

            val notification = builder.build()
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            manager.notify(20, notification)
        }

        // 여러줄의 내용을 넣을 수 있는 notification. 너무 길면 ... 으로 생략해준다.
        notiInboxBtn.setOnClickListener { view ->
            val builder = getNotification("style", "style notification")
            builder.setContentTitle("기본 노티 타이틀")
            builder.setContentText("기본 노티 내용")
            builder.setSmallIcon(android.R.drawable.ic_dialog_info)

            val inbox = NotificationCompat.InboxStyle(builder)
            inbox.setBigContentTitle("inbox 노티 타이틀")
            inbox.addLine("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")
            inbox.addLine("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb")
            inbox.addLine("cccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccc")
            inbox.addLine("dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd")

            val notification = builder.build()
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            manager.notify(30, notification)
        }
    }

    fun getNotification(id: String, name: String): NotificationCompat.Builder {
        var builder: NotificationCompat.Builder? = null

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val channel = NotificationChannel(id, name, NotificationManager.IMPORTANCE_HIGH)
            channel.enableLights(true)
            channel.lightColor = Color.RED
            channel.enableVibration(true)
            manager.createNotificationChannel(channel)

            builder = NotificationCompat.Builder(this, id)
        } else {
            builder = NotificationCompat.Builder(this)
        }

        return builder
    }
}
