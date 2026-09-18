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

    private var isPlayMusic = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_about)

        val btnMusic: ImageButton = findViewById(R.id.about_music)
        btnMusic.setOnClickListener {
            if(isPlayMusic){
                btnMusic.setImageResource(R.drawable.btn_music2)
                isPlayMusic = false
            }
            else{
                btnMusic.setImageResource(R.drawable.btn_music1)
                isPlayMusic = true
            }
        }

        val btnReback : MaterialButton = findViewById(R.id.aboutReback)
        btnReback.setOnClickListener{
            finish()
        }
    }

}