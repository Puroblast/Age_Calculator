package com.example.agecalculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.agecalculator.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by viewBinding()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val cal = Calendar.getInstance()
        val day = cal.get(Calendar.DAY_OF_MONTH)
        val month = cal.get(Calendar.MONTH)
        val year = cal.get(Calendar.YEAR)
        val dsl =
            DatePickerDialog.OnDateSetListener { view, currentYear, currentMonth, currentDay ->
                cal.set(Calendar.YEAR, currentYear)
                cal.set(Calendar.MONTH, currentMonth)
                cal.set(Calendar.DAY_OF_MONTH, currentDay)

                binding.selectedDateView.text = "Selected Date"
                binding.dateView.text = SimpleDateFormat("dd/MM/yyyy", Locale.US).format(cal.time)
                binding.totalMinutesView.text =
                    ((year * 525600 + month * 43800 + day * 1440) - (currentYear * 525600 + currentMonth * 43800 + currentDay * 1440)).toString()
                binding.tillDateView.text = "In minutes till date"

            }
        binding.selectDateBtn.setOnClickListener {
            DatePickerDialog(
                this,
                dsl,
                year,
                month,
                day
            ).show()
        }
    }

}