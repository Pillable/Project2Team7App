package com.example.pillreminder

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.pillreminder.Utils.DataHandler
import com.example.pillreminder.Utils.Reminder


class MainActivity : AppCompatActivity() {
    private lateinit var dataHandler: DataHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        dataHandler= DataHandler(
            getSharedPreferences(
                "jsonTimerArray",
                Context.MODE_PRIVATE
            )
        )

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //creates onclicklisner which starts the add reminder UI screen
        findViewById<Button>(R.id.add_reminder).setOnClickListener {
            val intent = Intent(this, AddReminder::class.java)
            startActivity(intent)
        }

        addAllReminders(dataHandler)
    }

    /**
     * Gets list of all reminders and adds them all to the scroll view
     *
     * @param dataHandler: DataHandler for the shared preference
     */
    private fun addAllReminders(dataHandler: DataHandler){
        val reminderList = dataHandler.getReminderList()
        for(index in 0 until reminderList.size){
            addReminderToScrollView(reminderList[index])
        }
    }

    /**
     * Creates reminder and adds it to the scroll view. It also initializes the scroll view onclick
     * listener which is meant to be clicked if the user wishes to delete a reminder
     *
     * @param reminder: reminder to add to scroll view
     */
    private fun addReminderToScrollView(reminder: Reminder){
        val linearLayout = findViewById<LinearLayout>(R.id.reminderScrollView)
        val inflater = this.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val reminderView: View = inflater.inflate(
            R.layout.single_reminder, findViewById(
                R.id.linearLayout
            ), false)
        reminderView.findViewById<TextView>(R.id.time).text = reminder.time
        reminderView.findViewById<TextView>(R.id.days).text = reminder.daysStr
        linearLayout.addView(reminderView)

        //sets onclick listener for the trash button so that it removes itself and removes its reference in json
        reminderView.findViewById<ImageButton>(R.id.trash).setOnClickListener {
            linearLayout.removeView(reminderView)
            dataHandler.deletePreference(reminder)
        }
    }
}