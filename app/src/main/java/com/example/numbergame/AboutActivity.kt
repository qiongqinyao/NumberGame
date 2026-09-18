package com.example.numbergame

import android.os.Bundle
import com.google.android.material.button.MaterialButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import android.widget.ImageButton

class AboutActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_about)

        val btnMusic: ImageButton = findViewById(R.id.about_music)
        updateMusicButton(btnMusic)
        btnMusic.setOnClickListener {
            MusicManager.toggle()
            updateMusicButton(btnMusic)
        }

        val btnReback : MaterialButton = findViewById(R.id.aboutReback)
        btnReback.setOnClickListener{
            finish()
        }
    }
    override fun onStart() {
        super.onStart()
        MusicManager.onActivityStart()
        val btnMusic: ImageButton = findViewById(R.id.about_music)
        updateMusicButton(btnMusic)
    }

    override fun onStop() {
        super.onStop()
        MusicManager.onActivityStop()
    }

    private fun updateMusicButton(btn: ImageButton) {
        if (MusicManager.isPlaying) {
            btn.setImageResource(R.drawable.btn_music1)
        }
        else {
            btn.setImageResource(R.drawable.btn_music2)
        }
    }
}