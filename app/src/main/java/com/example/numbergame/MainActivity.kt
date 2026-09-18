package com.example.numbergame

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat





class MainActivity : AppCompatActivity() {

    private val handler = Handler()

    private val jumpMenu = Runnable{
        startActivity(Intent(this, MenuActivity::class.java))
        finish()
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val splashImage: ImageView = findViewById(R.id.startBackground)

        splashImage.setOnClickListener{
            handler.removeCallbacks ( jumpMenu )
            jumpMenu.run()
        }



        }

    override fun onStart() {
        super.onStart()
        handler.postDelayed(jumpMenu, 5000)
    }
    override fun onStop() {
        super.onStop()
        handler.removeCallbacksAndMessages(null)
    }
}