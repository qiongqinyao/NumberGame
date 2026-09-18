package com.example.numbergame

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.ImageButton

class MenuActivity : AppCompatActivity() {
    private var isPlayMusic = true
    private lateinit var mediaPlayer: MediaPlayer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnMusic: ImageButton = findViewById(R.id.menu_music)
        mediaPlayer = MediaPlayer.create(this,R.raw.nizhan_hot)
        mediaPlayer.isLooping = true

        mediaPlayer.start()

        btnMusic.setOnClickListener {
            if(isPlayMusic){
                toggleMusic()
                btnMusic.setImageResource(R.drawable.btn_music2)
                isPlayMusic = false
            }
            else{
                toggleMusic()
                btnMusic.setImageResource(R.drawable.btn_music1)
                isPlayMusic = true
            }
        }

        val btnAbout: ImageButton = findViewById(R.id.menu_about)
        btnAbout.setOnClickListener{
            startActivity(Intent(this, AboutActivity::class.java))
        }

        val btnStart: ImageButton = findViewById(R.id.menu_start)
        btnStart.setOnClickListener {
            startActivity(Intent(this, SelectActivity::class.java))
        }
    }

    private fun toggleMusic(){
        if(isPlayMusic){
            mediaPlayer.pause()
        }
        else{
            mediaPlayer.start()
        }
    }

    override fun onStart() {
        super.onStart()

        if(isPlayMusic){
            mediaPlayer.start()
        }
    }

    override fun onPause() {
        super.onPause()
        super.onStop()
        if(::mediaPlayer.isInitialized){
            mediaPlayer.pause()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if(::mediaPlayer.isInitialized){
            mediaPlayer.stop()
            mediaPlayer.release()
        }
    }
}