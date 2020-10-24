package com.example.pillreminder.Utils

import android.widget.TimePicker

/**
 * from a TimePicker, gets the properly formatted time from it in the form hours:min:am/pm
 */
class Time(timeView: TimePicker){
    var time: String

    init {
        val hour = timeView.hour
        val min = timeView.minute

        /*
         I got the idea from here: https://stackoverflow.com/questions/6907968/how-to-convert-24-hr-format-time-in-to-12-hr-format
         It is just based on the conversion of military time hours to regular hours and adding a leading 0 to minutes
         */
        time = (if (hour > 12) hour % 12 else if(hour ==0) 12 else hour).toString() + ":" + (if (min < 10) "0$min" else min) + " " + if (hour >= 12) "PM" else "AM"

    }
}