package com.example.pillreminder.Utils

import android.content.SharedPreferences
import org.json.JSONArray
import org.json.JSONObject

/**
 * DataHandler class handles the storage, retrieval, and modification of the json arrays representing the
 * timers
 */
class DataHandler(val sharedPreferences: SharedPreferences) {
    private var currentPreferences: JSONArray = getPreferences()
    private lateinit var editor: SharedPreferences.Editor


    /**
     * Given a reminder class, it deletes it from the saved json
     *
     * @param reminder: reminder to find and delete
     */
    fun deletePreference(reminder: Reminder){
        updatePrefInfo()
        for(i in 0 until getPreferences().length()){
            val reminderJsonAr = getPreferences().getJSONArray(i)
            val days = reminderJsonAr.getString(1)
            val time = reminderJsonAr.getString(0)
            if(reminder.daysStr == days && reminder.time == time) currentPreferences.remove(i)

        }
        editor.putString("jsonDataArray", "{timerList: $currentPreferences}")
        editor.apply()
    }

    /**
     * Gets json array that represents all reminders in the form of
     * [[reminder time, reminder days], [reminder time, reminder days]]
     */
    private fun getPreferences(): JSONArray {
        val jsonObjStr: String? = sharedPreferences.getString("jsonDataArray", "{timerList: []}")
        if(jsonObjStr!=null) return JSONObject(jsonObjStr).getJSONArray("timerList")
        return JSONObject("{timerList: []}").getJSONArray("timerList")
    }

    /**
     * private method that gets current json from shared preferences
     */
    private fun updatePrefInfo(){
        currentPreferences = getPreferences()
        editor =  sharedPreferences.edit()
    }

    /**
     * From shared preference json array, it gets a list of Reminder classes
     */
    fun getReminderList(): ArrayList<Reminder> {
        val reminderList = ArrayList<Reminder>()
        for(i in 0 until getPreferences().length()){
            val reminderJsonAr = getPreferences().getJSONArray(i)
            val days = reminderJsonAr.getString(1)
            val time = reminderJsonAr.getString(0)
            reminderList.add(Reminder(days, time))
        }
        return reminderList
    }

    /**
     * Adds reminder class to shared preference and saves the shared preference
     */
    fun addToPreferences(reminder: Reminder){
        updatePrefInfo()
        currentPreferences.put(JSONArray("[\"${reminder.time}\",\" ${reminder.daysStr}\"]"))
        editor.putString("jsonDataArray", "{timerList: $currentPreferences}")
        editor.apply()
    }
}