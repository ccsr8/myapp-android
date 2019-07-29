package com.example.myapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import myapp.libraries.actions.Actions

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_main)

        startActivity(Actions.openDashboardIntent(this))
    }
}
