package com.example.numbergame

import android.content.Context
import android.media.MediaPlayer

object MusicManager {
    private lateinit var mediaPlayer: MediaPlayer
    var isPlaying : Boolean = true
        private set

    private var activityCount = 0
    private var isInitialized = false

    fun init(context: Context,musicResId: Int){
        if(isInitialized) return

        mediaPlayer = MediaPlayer.create(context.applicationContext,musicResId)
        mediaPlayer.isLooping = true
        isPlaying = true
        isInitialized = true

    }

    fun toggle(){
        if(!isInitialized) return
        if(isPlaying) {
            pause()
        }
        else{
            play()
        }
    }

    private fun play(){
        if (!isInitialized) return

        mediaPlayer.start()
        isPlaying = true
    }

    private fun pause(){
        if (!isInitialized || !mediaPlayer.isPlaying) return

        mediaPlayer.pause()
        isPlaying = false
    }

    fun onActivityStart(){
        activityCount++
        if(activityCount == 1 && isPlaying){
            play()
        }
    }

    fun onActivityStop() {
        activityCount--
        if (activityCount == 0) {
            pause()
        }
    }

    fun release() {
        if (isInitialized) {
            mediaPlayer.stop()
            mediaPlayer.release()
            isInitialized = false
        }
    }

}