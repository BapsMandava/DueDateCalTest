package com.example.emarsystest.view

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.emarsystest.R
import com.example.emarsystest.base.BaseActivity
import com.example.emarsystest.formartDateForDisplay
import com.example.emarsystest.viewmodel.DueDateViewModel
import kotlinx.android.synthetic.main.activity_due_date.*
import java.text.SimpleDateFormat
import java.util.*


class DueDateActivity : BaseActivity() {
    private lateinit var mViewModel: DueDateViewModel
    private var cal = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_due_date)
        mViewModel = ViewModelProviders.of(this).get(DueDateViewModel::class.java)

        // create an OnDateSetListener
        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int,
                                   dayOfMonth: Int) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInView()
            }
        }

        // when you click on the button, show DatePickerDialog that is set with OnDateSetListener
        ivb_date?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                DatePickerDialog(this@DueDateActivity,
                    dateSetListener,
                    // set DatePickerDialog to point to today's date when it loads up
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)).show()
            }
        })

        btnCalDueDate.setOnClickListener {
            var dueDate = mViewModel.calculateDueDate(cal,tvTrunAroudTime.text.toString().toInt())
            if(dueDate.equals("")){
                tvDueDate.setText(resources.getString(R.string.timeErrorMessage))
            }else{
                tvDueDate.setText(dueDate)
                tvDueDateTitle.visibility=View.VISIBLE
            }
            tvDueDate.visibility=View.VISIBLE

        }
    }

    private fun updateDateInView() {

        textview_date?.setText(formartDateForDisplay(cal))
    }


}
