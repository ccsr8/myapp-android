package com.example.koin.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.koin.R
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    val firstPresenter: MySimplePresenter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textview_hello.text = firstPresenter.sayHello()
    }
}
