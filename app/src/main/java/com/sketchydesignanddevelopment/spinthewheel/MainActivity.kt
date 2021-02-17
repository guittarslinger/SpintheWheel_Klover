package com.sketchydesignanddevelopment.spinthewheel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity(), MainFragment.OnButtonPressed {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //load main fragment
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragmentContainerView, MainFragment.newInstance(), "mainFragment")
            .commit()
    }

    //change to game screen with button press in main fragment
    override fun onButtonPressed() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainerView, GameScreenFragment.newInstance(), "gameFragment")
            .commit()
    }
}