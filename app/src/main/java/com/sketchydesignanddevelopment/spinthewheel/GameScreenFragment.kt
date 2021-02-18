package com.sketchydesignanddevelopment.spinthewheel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import okhttp3.*
import java.io.IOException


class GameScreenFragment: Fragment() {

    //library to make easy REST calls
    private val client = OkHttpClient()

    //initializing imageViews
    lateinit var wheel: ImageView
    lateinit var spinner: ImageView
    lateinit var spinButton: Button

    //new instance
    companion object{
        fun newInstance(): GameScreenFragment{

            return GameScreenFragment()
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_game_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //instantiate wheel, spinner, and buttons
        wheel = activity!!.findViewById(R.id.spinnyWheel)
        spinner = activity!!.findViewById(R.id.spinner)
        spinButton = activity!!.findViewById(R.id.spinButton)
        spinButton.setOnClickListener{
            startAnimation()
            spinWheel("https://mockbin.org/bin/539dc092-8367-414a-8892-ed3b2d666dbe")
        }
    }

    private fun spinWheel(url: String){
        val getSpinSpaces = Request.Builder().url(url).build()
        client.newCall(getSpinSpaces).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                println(getString(R.string.error))
                println(e)
            }

            override fun onResponse(call: Call, response: Response) {
                println(response)
            }
        })
    }

    private fun startAnimation(){
        val rotation: Animation = AnimationUtils.loadAnimation(context, R.anim.clockwise_rotation)
        wheel.startAnimation(rotation)
    }
}