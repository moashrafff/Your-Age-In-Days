package com.example.ageinminutesapp

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.ageinminutesapp.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnDatePicker.setOnClickListener { view->
            clickDatePicker(view)
        }


    }
    fun clickDatePicker(view: View){

        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

       val dpd = DatePickerDialog(this,DatePickerDialog.OnDateSetListener { datePicker, selectedYear, selectedMonth, selectedDay ->

            val selectedDate = "$selectedDay/${selectedMonth+1}/$selectedYear"

            binding.selectedDateTv.text = selectedDate

            val sdf =   SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)

            val theDate = sdf.parse(selectedDate)

            val theDateInDays = theDate!!.time / 86400000

            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

            val currentDateToDays = currentDate!!.time / 86400000

            val differenceInDays = currentDateToDays - theDateInDays

            binding.selectedDateInMinutesTv.text = differenceInDays.toString()

        },year,month,day)

        dpd.datePicker.setMaxDate(Date().time - 86400000)
        dpd.show()



    }
}