package com.example.emarsystest

import java.text.SimpleDateFormat
import java.util.*



fun formartDateForDisplay(date:Calendar):String{
        val myFormat = "dd-MM-yyyy HH:mm" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        return sdf.format(date.getTime())
    }

