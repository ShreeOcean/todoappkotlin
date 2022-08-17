package com.ocean.todoinkotlin.view.fragment

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import com.ocean.todoinkotlin.databinding.FragAddTodoBinding
import java.text.SimpleDateFormat
import java.util.*

const val DB_NAME = "todo.db"

class AddTODOFragment : Fragment() {

    private lateinit var binding: FragAddTodoBinding
    private lateinit var setDateListener: DatePickerDialog.OnDateSetListener
    private lateinit var setTimeListener: TimePickerDialog.OnTimeSetListener
    lateinit var myCalender: Calendar

    var finalDate = 0L
    var finalTime = 0L

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragAddTodoBinding.inflate(inflater, container, false)

        binding.etDate.setOnClickListener(View.OnClickListener {
            setDateListener()
        })
        binding.etTime.setOnClickListener(View.OnClickListener {
            setTimeListener()
        })

        return binding.root
    }

    private fun setTimeListener(){

    }

    private fun setDateListener() {
        myCalender = Calendar.getInstance()
        setDateListener =
            DatePickerDialog.OnDateSetListener() {  DatePicker, year: Int, month: Int, dayOfMonth: Int ->
                myCalender.set(Calendar.YEAR, year)
                myCalender.set(Calendar.MONTH, month)
                myCalender.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDate()

            }
        val datePickerDialog = DatePickerDialog(
            requireContext(), setDateListener, myCalender.get(Calendar.YEAR),
            myCalender.get(Calendar.MONTH), myCalender.get(Calendar.DAY_OF_MONTH))
        datePickerDialog.datePicker.minDate = System.currentTimeMillis()
        datePickerDialog.show()
    }

    private fun updateDate() {
        val myFormat = "d MMM, EEE, yyyy" //TODO: 5 Jan, Mon, 2020
        val simpleDateFormat = SimpleDateFormat(myFormat)
        finalDate = myCalender.time.time
        binding.etDate.setText(simpleDateFormat.format(myCalender.time))
        binding.outlinedTextFieldSetTime.visibility = View.VISIBLE
    }

}

