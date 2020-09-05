package com.example.nasa_project

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import java.util.*
import kotlin.concurrent.schedule


class FragmentLogo : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_logo, container, false)

        Timer().schedule(4000){
            // do something after 1 second
            val fragment = FragmentInfo()
            val fragmentManager = requireActivity().supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.frameLayout_fragment, fragment)
            fragmentTransaction.commit()
        }

        return view
    }

}