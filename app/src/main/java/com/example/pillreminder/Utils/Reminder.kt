package com.example.pillreminder.Utils

import java.io.Serializable


/**
 * Reminder class represents the data for the reminder
 */
class Reminder(): Serializable{
    var daysStr = ""
    var time = ""

    /**
     * From an array of true false values which represent the checkbox values, it converts it into
     * a properly formatted list of days. It also converts the time to a correct format.
     */
    constructor(days: BooleanArray, time: String) : this() {
        this.time = time
        val daysNames: Array<String> = arrayOf("Mon", "Tue", "Wed", "Thur", "Fri", "Sat", "Sun")
        for(index in days.indices){
            if(days[index]) daysStr += ", " + daysNames[index]
        }
        //remove first comma
        if(daysStr.isNotEmpty())daysStr = daysStr.substring(1)
    }

    /**
     * Sets day and time to parsed values while converting time to correct format
     */
    constructor(days: String, time: String) : this() {
        this.time = time
        this.daysStr = days
    }


}