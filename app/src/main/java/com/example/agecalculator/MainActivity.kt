package com.example.agecalculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.agecalculator.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*
import java.util.Calendar.*

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by viewBinding()
    private val myFormat = SimpleDateFormat("dd/MM/yyyy", Locale.US)
    private val calendar = Calendar.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews(){
        val day = calendar.get(DAY_OF_MONTH)
        val month = calendar.get(MONTH)
        val year = calendar.get(YEAR)
        val datesetlistener =
            DatePickerDialog.OnDateSetListener { _, selectedYear, selectedMonth, selectedDay ->
                calendar.set(YEAR, selectedYear)
                calendar.set(MONTH, selectedMonth)
                calendar.set(DAY_OF_MONTH, selectedDay)

                binding.selectedDateView.text = getString(R.string.selectedDateView)
                binding.dateView.text = myFormat.format(calendar.time)
                binding.totalMinutesView.text =
                    "${toMinutes(year,month,day) - toMinutes(selectedYear,selectedMonth,selectedDay)}"
                binding.tillDateView.text = getString(R.string.tillDateView)

            }
        binding.selectDateBtn.setOnClickListener {
            DatePickerDialog(
                this,
                datesetlistener,
                year,
                month,
                day
            ).show()
        }
    }

    private fun toMinutes(year:Int,month:Int,day:Int): Int {
        return (year * 525600 + month * 43800 + day * 1440)
    }


}