package com.example.numbergame

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton

class SelectActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_select)

        val btnReback : MaterialButton = findViewById(R.id.selectReback)
        btnReback.setOnClickListener{
            finish()
        }

        val btnMusic: ImageButton = findViewById(R.id.select_music)
        updateMusicButton(btnMusic)
        btnMusic.setOnClickListener {
            MusicManager.toggle()
            updateMusicButton(btnMusic)
        }

        findViewById<ImageButton>(R.id.Number0).setOnClickListener {goPlay(0)}
        findViewById<ImageButton>(R.id.Number1).setOnClickListener {goPlay(1)}
        findViewById<ImageButton>(R.id.Number2).setOnClickListener {goPlay(2)}
        findViewById<ImageButton>(R.id.Number3).setOnClickListener {goPlay(3)}
        findViewById<ImageButton>(R.id.Number4).setOnClickListener {goPlay(4)}
        findViewById<ImageButton>(R.id.Number5).setOnClickListener {goPlay(5)}
        findViewById<ImageButton>(R.id.Number6).setOnClickListener {goPlay(6)}
        findViewById<ImageButton>(R.id.Number7).setOnClickListener {goPlay(7)}
        findViewById<ImageButton>(R.id.Number8).setOnClickListener {goPlay(8)}
        findViewById<ImageButton>(R.id.Number9).setOnClickListener {goPlay(9)}

    }

    private fun goPlay(i: Int) {
        val intent = Intent(this, PlayActivity::class.java)
        intent.putExtra("SELECT_NUMBER",i)
        startActivity(intent)
    }

    override fun onStart() {
        super.onStart()
        MusicManager.onActivityStart()
        val btnMusic: ImageButton = findViewById(R.id.select_music)
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