package com.example.myapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import myapp.libraries.actions.Actions

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.activity_main)

        this.startActivity(Actions.openDataBindingIntent(this))
    }
}
