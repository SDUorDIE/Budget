package com.example.budget

import android.app.DatePickerDialog
import android.content.Intent
import android.icu.util.Calendar
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private val morningViewValues = IntArray(7) { 0 }
    private val afternoonViewValues = IntArray(7) { 0 }
    private val expenseViewValues = IntArray(7) { 0 }
    private val dateTextViewValues = arrayOfNulls<String>(7)
    private var currentDay = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val dateTextView = findViewById<TextView>(R.id.dateTextView)
        val afternoonViewEditText = findViewById<EditText>(R.id.afternoonViewEditText)
        val morningViewEditText = findViewById<EditText>(R.id.morningViewEditText)
        val expenseViewEditText = findViewById<EditText>(R.id.expenseViewEditText)
        val dateBtn = findViewById<Button>(R.id.DateBtn)
        val saveBtn = findViewById<Button>(R.id.Savebtn)
        val clearBtn = findViewById<Button>(R.id.Clearbtn)
        val detailsBtn = findViewById<Button>(R.id.Detailsbtn)


        val calendar = Calendar.getInstance()
        updateDateInView(dateTextView, calendar)


        dateBtn.setOnClickListener {
            DatePickerDialog(
                this,
                { _, year, month, dayOfMonth ->
                    calendar.set(year, month, dayOfMonth)
                    updateDateInView(dateTextView, calendar)
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        saveBtn.setOnClickListener {

            afternoonViewValues[currentDay] = afternoonViewEditText.text.toString().toIntOrNull() ?: 0
            morningViewValues[currentDay] = morningViewEditText.text.toString().toIntOrNull() ?: 0
            expenseViewValues[currentDay] = expenseViewEditText.text.toString().toIntOrNull() ?: 0
            dateTextViewValues[currentDay] = dateTextView.text.toString()

            Toast.makeText(this, "Your Details For Day ${currentDay + 1} Have Been Saved", Toast.LENGTH_SHORT).show()
            currentDay = (currentDay + 1) % 7
        }


        clearBtn.setOnClickListener {
            clearFields(afternoonViewEditText, morningViewEditText, expenseViewEditText)
        }


        detailsBtn.setOnClickListener {
            val intent = Intent(this, DetailedViewActivity::class.java)
            intent.putExtra("Afternoon", afternoonViewValues)
            intent.putExtra("Morning", morningViewValues)
            intent.putExtra("Expense", expenseViewValues)
            intent.putExtra("Date", dateTextViewValues)
            startActivity(intent)
        }
    }


    private fun updateDateInView(dateTextView: TextView, calendar: Calendar) {
        val formattedDate = "${calendar.get(Calendar.YEAR)}-${String.format("%02d", calendar.get(
            Calendar.MONTH) + 1)}-${String.format("%02d", calendar.get(Calendar.DAY_OF_MONTH))}"
        dateTextView.text = formattedDate
    }

    private fun clearFields(afternoonViewEditText: EditText, morningViewEditText: EditText, expenseViewEditText: EditText) {
        afternoonViewEditText.text.clear()
        morningViewEditText.text.clear()
        expenseViewEditText.text.clear()
    }
}


