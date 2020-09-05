package com.example.nasa_project

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.nasa_project.childFragments.RoverCuriosityFragment
import com.example.nasa_project.childFragments.RoverOpportunityFragment
import com.example.nasa_project.childFragments.RoverSpiritFragment
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_screen_slide_page.view.*

class ScreenSlidePageFragment : Fragment() {
    // When requested, this adapter returns a DemoObjectFragment,
    // representing an object in the collection.
    private lateinit var demoCollectionAdapter: DemoCollectionAdapter
    private lateinit var viewPager: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_screen_slide_page, container, false)

        demoCollectionAdapter = DemoCollectionAdapter(this)
        viewPager = view.findViewById(R.id.pager)
        viewPager.adapter = demoCollectionAdapter

        TabLayoutMediator(view.findViewById(R.id.tab_layout), viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Curiosity"
                1 -> tab.text = "Opportunity"
                else -> tab.text = "Spirit"
            }
        }.attach()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }
}

class DemoCollectionAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment = when (position) {
        0 -> RoverCuriosityFragment()
        1 -> RoverOpportunityFragment()
        else -> RoverSpiritFragment()
    }
}
