package com.example.nasa_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    private var flogo : FragmentLogo? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        flogo = FragmentLogo()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout_fragment, flogo!!)
        transaction.commit()
    }
}