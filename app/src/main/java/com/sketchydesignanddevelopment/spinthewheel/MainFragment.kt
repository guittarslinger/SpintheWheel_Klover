package com.sketchydesignanddevelopment.spinthewheel

import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import java.util.jar.Manifest

class MainFragment:Fragment() {

    //lateinit variable for interface listener
    private lateinit var listener: OnButtonPressed

    //new instance
    companion object {
        fun newInstance():MainFragment {
            return MainFragment()
        }
    }

    //inflating view
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        //checking that activity implements interface
        if (context is OnButtonPressed) {
            listener = context
        } else {
            throw ClassCastException(
                "$context must implement OnButtonPressed."
            )
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //create button and set listener to change fragments
        val startGameButton = activity!!.findViewById<Button>(R.id.spinnyButton)
        startGameButton.setOnClickListener {
            listener.onButtonPressed()
        }
    }

    //interface for changing fragments
    interface OnButtonPressed{
        fun onButtonPressed()
    }

}