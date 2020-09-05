package com.example.nasa_project.childFragments

import android.icu.number.NumberFormatter.with
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import com.bumptech.glide.Glide
import com.example.nasa_project.R
import com.example.nasa_project.data.MyAdapterCuriosityRover
import com.example.nasa_project.data.MyAdapterOpportunityRover
import com.example.nasa_project.data.api.ApiServiceCuriosityObject
import com.example.nasa_project.data.api.ApiServiceOpportunityObject
import com.example.nasa_project.data.model.Photo
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_rover_curiosity.view.*
import kotlinx.android.synthetic.main.fragment_rover_curiosity.*
import kotlinx.android.synthetic.main.fragment_rover_opportunity.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.awaitResponse
import java.lang.Exception

class RoverOpportunityFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_rover_opportunity, container, false)

        // Gestione DatePicker per selezionare il giorno nel quale sono state scattate le foto dal rover
        val builder: MaterialDatePicker.Builder<*> = MaterialDatePicker.Builder.datePicker()
        builder.setTitleText("Seleziona una data")

        lateinit var year: String
        lateinit var day: String
        var month: String = ""
        lateinit var date: String

        val picker: MaterialDatePicker<*> = builder.build()

        //To apply a dialog
        builder.setTheme(R.style.ThemeOverlay_MaterialComponents_MaterialCalendar)
        //To apply the fullscreen:
        builder.setTheme(R.style.ThemeOverlay_MaterialComponents_MaterialCalendar_Fullscreen)

        view.txtViewDate.setOnClickListener {
            fragmentManager?.let { manager ->
                picker.show(manager, "DatePickerDialog")
            }
        }

        picker.addOnPositiveButtonClickListener {

            val monthName = picker.headerText.split(" ")[1].split(",")[0].decapitalize()

            Log.i("AIUTO","monthName "+monthName.decapitalize())
            when (monthName) {
                "gen" -> {
                    month = "1"
                }
                "jan" -> {
                    month = "1"
                }
                "feb" -> {
                    month = "2"
                }
                "mar" -> {
                    month = "3"
                }
                "apr" -> {
                    month = "4"
                }
                "mag" -> {
                    month = "5"
                }
                "may" -> {
                    month = "5"
                }
                "giu" -> {
                    month = "6"
                }
                "jun" -> {
                    month = "6"
                }
                "lug" -> {
                    month = "7"
                }
                "jul" -> {
                    month = "7"
                }
                "ago" -> {
                    month = "8"
                }
                "aug" -> {
                    month = "8"
                }
                "set" -> {
                    month = "9"
                }
                "sep" -> {
                    month = "9"
                }
                "ott" -> {
                    month = "10"
                }
                "oct" -> {
                    month = "10"
                }
                "nov" -> {
                    month = "11"
                }
                "dic" -> {
                    month = "12"
                }
                "dec" -> {
                    month = "12"
                }
                else -> {
                    Log.i("dataAAAAAAAAA", "Errore Mese")
                }
            }
            day = picker.headerText.split(" ")[0]
            year = picker.headerText.split(" ")[2]
            date = "$year-$month-$day"


            Log.i("AIUTO","headerTex"+picker.headerText)
            Log.i("AIUTO","month "+month)
            Log.i("AIUTO","day "+day)
            Log.i("AIUTO","year "+year)
            view.txtViewDate.text = date

            // Gestione Recycler View
            val dataListOpportunityRover: MutableList<Photo> = mutableListOf()
            lateinit var myAdapterOpportunityRover: MyAdapterOpportunityRover
            var listOpportunityRover: ArrayList<Photo>? = null

            GlobalScope.launch(Dispatchers.Main) {

                val response = ApiServiceOpportunityObject.RETROFIT_SERVICE.getPhotosOpportunity(
                    "rGe3EKNcXa3FrQpqJkoEyCydYePEKtf7BhRMncyJ",date
                ).awaitResponse()

                if(response.isSuccessful && response.body() != null){
                    try {
                        if (response.body()?.photos?.isEmpty()!!) {
                            val snackBar: Snackbar = Snackbar.make(
                                activity!!.findViewById(android.R.id.content),
                                "Non ci sono foto in questa giornata", Snackbar.LENGTH_LONG
                            )
                            snackBar.show()
                        } else {
                            myAdapterOpportunityRover =
                                MyAdapterOpportunityRover(dataListOpportunityRover)

                            recyclerViewOpportunityRover.layoutManager = LinearLayoutManager(context)
                            recyclerViewOpportunityRover.addItemDecoration(
                                DividerItemDecoration(
                                    context,
                                    OrientationHelper.VERTICAL
                                )
                            )
                            recyclerViewOpportunityRover.adapter = myAdapterOpportunityRover

                            listOpportunityRover = (response.body()?.photos as ArrayList<Photo>?)!!
                            dataListOpportunityRover.addAll(listOpportunityRover!!)
                            myAdapterOpportunityRover.notifyDataSetChanged()
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                        val snackBar: Snackbar = Snackbar.make(
                            activity!!.findViewById(android.R.id.content),
                            "Errore" + e.toString(), Snackbar.LENGTH_LONG
                        )
                        snackBar.show()
                    }
                } else {
                    val snackBar: Snackbar = Snackbar.make(
                        activity!!.findViewById(android.R.id.content),
                        "Errore", Snackbar.LENGTH_LONG
                    )
                    snackBar.show()
                }
            }
        }

        view.txtViewRoverName.setOnClickListener {

            val fragment = RoverOpportunityInfoFragment()
            val fragmentManager = requireActivity().supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.frameLayout_fragment, fragment).addToBackStack(null)
            fragmentTransaction.commit()
        }

        return view
    }


}