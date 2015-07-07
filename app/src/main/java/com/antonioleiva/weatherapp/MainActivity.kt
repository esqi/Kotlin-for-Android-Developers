package com.antonioleiva.weatherapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.activity_main.message

public class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        message.text = "Hello Kotlin!"
    }

}