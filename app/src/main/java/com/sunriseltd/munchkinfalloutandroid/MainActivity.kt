package com.sunriseltd.munchkinfalloutandroid

import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.media.MediaPlayer
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView

class MainActivity : AppCompatActivity() {

    private lateinit var mp_click: MediaPlayer
    private lateinit var mp_start: MediaPlayer
    private lateinit var rad_sound: MediaPlayer

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        //hide title bar
        window.requestFeature(Window.FEATURE_NO_TITLE)
        //make fullscreen
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_main)

        rad_sound = MediaPlayer.create(this, R.raw.ui_pipboy_radiation_b_03)

        mp_click = MediaPlayer.create(this, R.raw.ui_menu_ok)
        mp_start = MediaPlayer.create(this, R.raw.ui_pipboy_light_on)
        mp_start.start()

        //level
        var btnLevelUp = findViewById<Button>(R.id.levelUp)
        var btnLevelDown = findViewById<Button>(R.id.levelDown)
        var levelProgress = findViewById<ProgressBar>(R.id.levelBar)
        var levelCount = findViewById<TextView>(R.id.levelCount)

        //radiation
        var btnRadUp = findViewById<Button>(R.id.radUp)
        var btnRadDown = findViewById<Button>(R.id.radDown)
        var radProgress = findViewById<ProgressBar>(R.id.radBar)
        var radCount = findViewById<TextView>(R.id.radCount)
        var radLevelImg = findViewById<ImageView>(R.id.radLevelImg)
        var radImgCount = findViewById<TextView>(R.id.rad_mess_count)


        //уровень героя
        btnLevelUp.setOnClickListener {
            if(levelProgress.progress != 10){
                mp_click.start()
                levelProgress.progress ++
                levelCount.text = levelProgress.progress.toString()
            }
        }
        btnLevelDown.setOnClickListener {
            if(levelProgress.progress != 0 && levelProgress.progress <= 10){
                mp_click.start()
                levelProgress.progress --
                levelCount.text = levelProgress.progress.toString()
            }
        }

        fun radChangeIcon(){
            if(radProgress.progress == 0 && radProgress.progress < 10){
                radLevelImg.setImageResource(R.drawable.rad_level_0)
                rad_sound.start()
            }else if(radProgress.progress == 10 && radProgress.progress < 25){
                radLevelImg.setImageResource(R.drawable.rad_level_10)
                rad_sound.start()
            }else if(radProgress.progress == 25 && radProgress.progress < 45){
                radLevelImg.setImageResource(R.drawable.rad_level_25)
                rad_sound.start()
            }else if(radProgress.progress == 45){
                radLevelImg.setImageResource(R.drawable.rad_level_45)
                rad_sound.start()
            }
        }

        //радиация героя
        btnRadUp.setOnClickListener {
            if(radProgress.progress != 50){
                mp_click.start()
                radProgress.progress ++
                radCount.text = radProgress.progress.toString()
                radImgCount.text = radProgress.progress.toString()
                radChangeIcon()
            }
        }
        btnRadDown.setOnClickListener {
            if(radProgress.progress != 0 && radProgress.progress <= 50){
                mp_click.start()
                radProgress.progress --
                radCount.text = radProgress.progress.toString()
                radImgCount.text = radProgress.progress.toString()
                radChangeIcon()
            }
        }
    }
}
