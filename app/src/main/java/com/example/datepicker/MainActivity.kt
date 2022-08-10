package com.example.datepicker

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.TimePicker
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener,TimePickerDialog.OnTimeSetListener {

    var day =0
    var month =0
    var year =0
    var hour =0
    var minute = 0

    var savedDay =0
    var savedMonth =0
    var savedYear =0
    var savedHour =0
    var savedMinute = 0

    lateinit var simpleDateFormat: SimpleDateFormat
    lateinit var date:String
    lateinit var calendar: Calendar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pickdate()
        calendar= Calendar.getInstance()

        simpleDateFormat= SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
        date = simpleDateFormat.format(calendar.time)
        println("ini loh $simpleDateFormat")
        println("ini loh $date")
        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        val currentDate = sdf.format(Date())
        System.out.println(" C DATE is  "+currentDate)
    }
    private fun getDateTimeCalendar(){
        val cal = Calendar.getInstance()
        day = cal.get(Calendar.DAY_OF_MONTH)
        month = cal.get(Calendar.MONTH)
        year = cal.get(Calendar.YEAR)
        hour = cal.get(Calendar.HOUR)
        minute = cal.get(Calendar.MINUTE)
    }
    private fun pickdate(){
        val btn_timePicker = findViewById<Button>(R.id.btn_timepicker)
        btn_timePicker.setOnClickListener{
            getDateTimeCalendar()
            DatePickerDialog(this,this,year,month,day).show()
        }
    }

    //override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        savedDay = dayOfMonth
        savedMonth = month
        savedYear = year

        getDateTimeCalendar()
        TimePickerDialog(this,this,hour,minute,true).show()
    }

    //override fun onTimeSet(p0: TimePicker?, p1: Int, p2: Int) {
    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        savedHour = hourOfDay
        savedMinute = minute
        val tv_textTime = findViewById<TextView>(R.id.tv_textTime)
        tv_textTime.text = "$savedDay-$savedMonth-$savedYear\n Hour: $savedHour Minute:$savedMinute"
    }
}