package com.example.numbergame

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.ImageButton

class MenuActivity : AppCompatActivity() {
    private var isPlayMusic = true

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

        val btnAbout: ImageButton = findViewById(R.id.menu_about)
        btnAbout.setOnClickListener{
            startActivity(Intent(this, AboutActivity::class.java))
        }

        val btnStart: ImageButton = findViewById(R.id.menu_start)
        btnStart.setOnClickListener {
            startActivity(Intent(this, SelectActivity::class.java))
        }
    }
}