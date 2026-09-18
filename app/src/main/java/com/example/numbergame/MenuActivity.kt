package com.example.numbergame

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.ImageButton
import androidx.activity.OnBackPressedCallback

class MenuActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnAbout: ImageButton = findViewById(R.id.menu_about)
        btnAbout.setOnClickListener{
            startActivity(Intent(this, AboutActivity::class.java))
        }

        val btnStart: ImageButton = findViewById(R.id.menu_start)
        btnStart.setOnClickListener {
            startActivity(Intent(this, SelectActivity::class.java))
        }

        val btnMusic: ImageButton = findViewById(R.id.menu_music)
        updateMusicButton(btnMusic)

        btnMusic.setOnClickListener {
            MusicManager.toggle()
            updateMusicButton(btnMusic)
        }

    }

    override fun onStart() {
        super.onStart()
        MusicManager.onActivityStart()
        val btnMusic: ImageButton = findViewById(R.id.menu_music)
        updateMusicButton(btnMusic)
    }

    override fun onStop() {
        super.onStop()
        MusicManager.onActivityStop()
    }

    // 页面销毁
    override fun onDestroy() {
        super.onDestroy()
        // isFinishing : 用户主动退出
        if (isFinishing) {
            MusicManager.release()
        }
    }

    // 根据播放状态更新按钮图标
    private fun updateMusicButton(btn: ImageButton) {
        if (MusicManager.isPlaying) {
            // 播放中 → 显示暂停图标（点一下就暂停）
            btn.setImageResource(R.drawable.btn_music1)
        }
        else {
            // 暂停中 → 显示播放图标（点一下就播放）
            btn.setImageResource(R.drawable.btn_music2)
        }
    }

}