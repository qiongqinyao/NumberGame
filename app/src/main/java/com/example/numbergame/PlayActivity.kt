package com.example.numbergame

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.os.Handler
import android.widget.ImageButton

class PlayActivity : AppCompatActivity() {

    private val numberBackgrounds = intArrayOf(
        R.drawable.bg0, R.drawable.bg1, R.drawable.bg2, R.drawable.bg3, R.drawable.bg4,
        R.drawable.bg5, R.drawable.bg6, R.drawable.bg7, R.drawable.bg8, R.drawable.bg9
    )

    private val animResArray = intArrayOf(
        R.drawable.anim_on0, R.drawable.anim_on1, R.drawable.anim_on2, R.drawable.anim_on3, R.drawable.anim_on4,
        R.drawable.anim_on5, R.drawable.anim_on6, R.drawable.anim_on7, R.drawable.anim_on8, R.drawable.anim_on9
    )

    private val handler = Handler()
    private lateinit var frames : AnimationDrawable
    private var frameIndex = 0
    private val FRAME_DELAY : Long = 100
    private val nextFrame = Runnable{showNextFrame()}
    private lateinit var AnimView : ImageView

    private fun showNextFrame(){
        if(frameIndex >= frames.numberOfFrames) return
        AnimView.setImageDrawable(frames.getFrame((frameIndex)))
        frameIndex++

        handler.postDelayed(nextFrame,FRAME_DELAY)
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_play)


        val selectNumber = intent.getIntExtra("SELECT_NUMBER",-1)

        if (selectNumber !in numberBackgrounds.indices){
            finish()
            return
        }

        val background = findViewById<ImageView>(R.id.playBackground)
        background.setImageResource(numberBackgrounds[selectNumber])

        AnimView = findViewById<ImageView>(R.id.playAnimation)
        frames = resources.getDrawable(animResArray[selectNumber],theme) as AnimationDrawable
        frameIndex = 0

        val btnMusic: ImageButton = findViewById(R.id.play_music)
        updateMusicButton(btnMusic)
        btnMusic.setOnClickListener {
            MusicManager.toggle()
            updateMusicButton(btnMusic)
        }


        val Reback = findViewById<com.google.android.material.button.MaterialButton>(R.id.playReback)
        Reback.setOnClickListener {
            finish()
        }
    }



    override fun onStart() {
        super.onStart()
        // 音乐计数+1
        MusicManager.onActivityStart()
        // 同步按钮状态
        val btnMusic: ImageButton = findViewById(R.id.play_music)
        updateMusicButton(btnMusic)

        // 动画重新开始
        frameIndex = 0
        handler.post(nextFrame)
    }

    override fun onStop() {
        super.onStop()
        // 音乐计数-1
        MusicManager.onActivityStop()
        // 停止动画
        handler.removeCallbacks(nextFrame)
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(nextFrame)
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