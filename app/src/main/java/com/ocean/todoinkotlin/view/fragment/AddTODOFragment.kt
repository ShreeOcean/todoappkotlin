package com.ocean.todoinkotlin.view.fragment

import android.R
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.ocean.todoinkotlin.databinding.FragAddTodoBinding
import com.ocean.todoinkotlin.model.TodoModel
import com.ocean.todoinkotlin.roomDB.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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

    private val labels = arrayListOf("Personal", "Business", "Insurance", "Shopping", "Banking")

    val db by lazy {
        AppDatabase.getDatabase(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragAddTodoBinding.inflate(inflater, container, false)

        setUpSpinner()

        binding.etDate.setOnClickListener(View.OnClickListener {
            setDateListener()
        })
        binding.etTime.setOnClickListener(View.OnClickListener {
            setTimeListener()
        })
        binding.saveBtn.setOnClickListener( View.OnClickListener {

            val category = binding.spinnerCategory.selectedItem.toString()
            val description = binding.outlinedTextFieldTittle.editText?.text.toString()
            val title = binding.outlinedTextFieldTittle.editText?.text.toString()

            GlobalScope.launch(Dispatchers.Main) {
                val id = withContext(Dispatchers.IO) {
                    return@withContext db.todoDao().insertToDo(
                        TodoModel(
                            title,
                            description,
                            category,
                            finalDate,
                            finalTime
                        )
                    )
                }
//                finish()
            }
        })

        return binding.root
    }

    private fun setUpSpinner() {
        val adapter = ArrayAdapter<String>(requireContext(), R.layout.simple_spinner_dropdown_item, labels)
        labels.sort()
        binding.spinnerCategory.adapter = adapter
    }

    private fun setTimeListener(){

        myCalender = Calendar.getInstance()
        setTimeListener = TimePickerDialog.OnTimeSetListener(){  TimePicker, hourOfDay: Int, minute:Int ->
            myCalender.set(Calendar.HOUR_OF_DAY, hourOfDay)
            myCalender.set(Calendar.MINUTE, minute)
            updateTime()
        }
        val timePickerDialog =TimePickerDialog(
            requireContext(),setTimeListener,myCalender.get(Calendar.HOUR_OF_DAY),
            myCalender.get(Calendar.MINUTE), false)
        timePickerDialog.show()
    }

    private fun updateTime() {
        val myFormat = "h:mm a"
        val simpleDateFormat = SimpleDateFormat(myFormat)
        finalTime =myCalender.time.time
        binding.etTime.setText(simpleDateFormat.format(myCalender.time))
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

