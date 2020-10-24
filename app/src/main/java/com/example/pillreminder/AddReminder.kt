package com.example.pillreminder

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TimePicker
import androidx.appcompat.app.AppCompatActivity
import com.example.pillreminder.Utils.DataHandler
import com.example.pillreminder.Utils.Reminder
import com.example.pillreminder.Utils.Time


class AddReminder : AppCompatActivity() {

    //list of IDs for all the check buttons in order from monday to sunday
    private val checkBoxList = intArrayOf(
        R.id.mon_button,
        R.id.tues_button,
        R.id.wens_button,
        R.id.thur_button,
        R.id.fri_button,
        R.id.sat_button,
        R.id.sun_button
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_reminder)

        val sharedPreferences = getSharedPreferences("jsonTimerArray", Context.MODE_PRIVATE)

        findViewById<Button>(R.id.done).setOnClickListener {
            startMainActivity(true, sharedPreferences)
        }

        findViewById<Button>(R.id.cancel).setOnClickListener {
            startMainActivity(false, sharedPreferences)
        }

    }

    /**
     *  Starts main activity and optionally parses it the reminder
     *
     *  @param putExtra: tells method if you want to put the reminder as an extra
     */
    private fun startMainActivity(putExtra: Boolean = false, sharedPreferences: SharedPreferences){
        val reminder = Reminder(
            getButtonArray(checkBoxList),
            Time(findViewById(R.id.editTextTime)).time
        )

        if(putExtra) DataHandler(sharedPreferences).addToPreferences(reminder)

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }



    /**
     * Given list of button ids for all
     */
    private fun getButtonArray(idList: IntArray): BooleanArray {
        val daysBool = ArrayList<Boolean>()
        for (index in idList.indices) {
            daysBool.add(findViewById<CheckBox>(idList[index]).isChecked)
        }
        return daysBool.toBooleanArray()
    }
}