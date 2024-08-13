package com.ighorosipov.coinapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ighorosipov.coinapp.util.di.appComponent

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun inject() {
        appComponent().inject(this)
    }
}