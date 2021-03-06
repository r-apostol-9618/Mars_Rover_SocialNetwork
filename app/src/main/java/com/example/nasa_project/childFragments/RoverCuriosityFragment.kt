package com.example.nasa_project.childFragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import com.example.nasa_project.R
import com.example.nasa_project.ScreenSlidePageFragment
import com.example.nasa_project.data.MyAdapterCuriosityRover
import com.example.nasa_project.data.api.ApiServiceCuriosityObject
import com.example.nasa_project.data.model.Photo
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_rover_curiosity.*
import kotlinx.android.synthetic.main.fragment_rover_curiosity.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.awaitResponse
import java.lang.Exception
import kotlin.collections.ArrayList


class RoverCuriosityFragment : Fragment() {

//    private var apiKey = Resources.getSystem().getString(R.string.api_key)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_rover_curiosity, container, false)

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
                "giu" -> {
                    month = "6"
                }
                "lug" -> {
                    month = "7"
                }
                "ago" -> {
                    month = "8"
                }
                "set" -> {
                    month = "9"
                }
                "ott" -> {
                    month = "10"
                }
                "nov" -> {
                    month = "11"
                }
                "dic" -> {
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
            val dataListCuriosityRover: MutableList<Photo> = mutableListOf()
            lateinit var myAdapterCuriosityRover: MyAdapterCuriosityRover
            var listCuriosityRover: ArrayList<Photo>? = null

            GlobalScope.launch(Dispatchers.Main) {

                val response = ApiServiceCuriosityObject.RETROFIT_SERVICE.getPhotosCuriosity(
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
                            myAdapterCuriosityRover =
                                MyAdapterCuriosityRover(dataListCuriosityRover)

                            recyclerViewCuriosityRover.layoutManager = LinearLayoutManager(context)
                            recyclerViewCuriosityRover.addItemDecoration(
                                DividerItemDecoration(
                                    context,
                                    OrientationHelper.VERTICAL
                                )
                            )
                            recyclerViewCuriosityRover.adapter = myAdapterCuriosityRover

                            listCuriosityRover = (response.body()?.photos as ArrayList<Photo>?)!!
                            dataListCuriosityRover.addAll(listCuriosityRover!!)
                            myAdapterCuriosityRover.notifyDataSetChanged()
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

            val fragment = RoverCuriosityInfoFragment()
            val fragmentManager = requireActivity().supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.frameLayout_fragment, fragment).addToBackStack(null)
            fragmentTransaction.commit()
        }

        return view
    }


}