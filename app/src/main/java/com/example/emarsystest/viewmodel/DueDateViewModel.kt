package com.example.emarsystest.viewmodel

import androidx.lifecycle.ViewModel
import com.example.emarsystest.formartDateForDisplay
import java.util.*


class DueDateViewModel : ViewModel() {
    var finalDueDate:String = ""

    fun calculateDueDate(reportDate:Calendar,turnAroundTime:Int):String {
        var dueDate:Calendar= Calendar.getInstance()
        println(reportDate.time)
        if (checkIfSubmittedDateIsValid(reportDate)) {
            dueDate = getWorkingDueDate(reportDate, turnAroundTime)
            finalDueDate = formartDateForDisplay(dueDate)
        }
        return finalDueDate
    }

    fun checkIfSubmittedDateIsValid(date: Calendar)= (date.time.hours >= 9 && date.time.hours <= 16)

    fun getWorkingDueDate(date: Calendar, days: Int): Calendar {
        date.add(Calendar.DATE, days)
        if (date.time.day == 6 || date.time.day == 0) {
            getWorkingDueDate(date, 1)
        }
        return date
    }

}