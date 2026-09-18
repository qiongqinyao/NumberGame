package com.example.numbergame

import android.app.Application

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        // 全局初始化背景音乐
        MusicManager.init(this, R.raw.nizhan_hot)
    }
}