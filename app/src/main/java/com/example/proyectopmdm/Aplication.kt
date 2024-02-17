package com.example.proyectopmdm

import android.app.Application

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // Inicia la reproducción de la música al iniciar la aplicación
        MusicPlayer.start(this, R.raw.cancion)
    }
}
