package com.sketchydesignanddevelopment.spinthewheel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import okhttp3.*
import java.io.IOException

class GameScreenFragment: Fragment() {

    //library to make easy REST calls
    private val client = OkHttpClient()

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
        val wheel = activity!!.findViewById<ImageView>(R.id.spinnyWheel)
        val spinner = activity!!.findViewById<ImageView>(R.id.spinner)
        val spinButton = activity!!.findViewById<Button>(R.id.spinButton)
        spinButton.setOnClickListener{
            wheel.animate()
            spinner.animate()
            spinWheel("http://mockbin.org/bin/539dc092-8367-414a-8892-ed3b2d666dbe")
        }
    }

    private fun spinWheel(url: String){

        val getSpinSpaces = Request.Builder().url(url).build()
        client.newCall(getSpinSpaces).enqueue(object:Callback{
            override fun onFailure(call: Call, e: IOException) {
                println(getString(R.string.error))
            }

            override fun onResponse(call: Call, response: Response) {
                println(response)
            }
        })
    }
}