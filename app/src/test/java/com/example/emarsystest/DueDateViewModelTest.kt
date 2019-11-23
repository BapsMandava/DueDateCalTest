package com.example.emarsystest

import com.example.emarsystest.viewmodel.DueDateViewModel
import org.junit.Test

import org.junit.Assert.*
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class DueDateViewModelTest {
    var dueDateViewModel = DueDateViewModel()

    @Test
    fun `get duedate on working day`() {
        var date = Calendar.getInstance()
        date.set(2019,11,25,10,23,0)
        val value = dueDateViewModel.calculateDueDate(date,2)
        val expectedValue = "27-12-2019 10:23"
        assertEquals(value,expectedValue)
    }

    @Test
    fun `check if time is in working hours`() {
        var date = Calendar.getInstance()
        date.set(2019,11,25,10,23,0)
        assertTrue(dueDateViewModel.checkIfSubmittedDateIsValid(date))

    }

    @Test
    fun `check if time is in non working hours`() {
        var date = Calendar.getInstance()
        date.set(2019,11,25,19,23,0)
        assertFalse(dueDateViewModel.checkIfSubmittedDateIsValid(date))

    }
}
