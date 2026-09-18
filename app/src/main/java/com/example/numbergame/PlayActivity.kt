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

    private var isPlayMusic = true
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

        val btnMusic: ImageButton = findViewById(R.id.play_music)
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

        val Reback = findViewById<com.google.android.material.button.MaterialButton>(R.id.playReback)
        Reback.setOnClickListener {
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        frameIndex = 0                       // 重新开始
        handler.post(nextFrame)
    }


    override fun onStop() {
        super.onStop()
        handler.removeCallbacks (nextFrame )
    }
}