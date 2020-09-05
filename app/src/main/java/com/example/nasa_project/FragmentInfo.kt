package com.example.nasa_project

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.nasa_project.childFragments.RoverCuriosityInfoFragment
import kotlinx.android.synthetic.main.fragment_info.view.*
import kotlinx.android.synthetic.main.fragment_rover_curiosity.view.*
import java.util.*
import kotlin.concurrent.schedule

class FragmentInfo : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_info, container, false)

        view.btn_info_read.setOnClickListener {

            val fragment = ScreenSlidePageFragment()
            val fragmentManager = requireActivity().supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.frameLayout_fragment, fragment).addToBackStack(null)
            fragmentTransaction.commit()
        }


        return view
    }

}