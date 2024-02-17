package com.example.proyectopmdm

import android.content.Context
import android.media.MediaPlayer

object MusicPlayer {
    private var mediaPlayer: MediaPlayer? = null

    fun start(context: Context, resourceId: Int) {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(context.applicationContext, resourceId)
            mediaPlayer?.isLooping = true
        }
        mediaPlayer?.start()
    }

    fun stop() {
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}