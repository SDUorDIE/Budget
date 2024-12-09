package com.example.budget

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetailedViewActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detailed_view)

        val afternoonViewEditText = intent.getIntArrayExtra("Afternoon")?: IntArray(7)
        val morningViewEditText = intent.getIntArrayExtra("Morning")?: IntArray(7)
        val expenseViewEditText = intent.getIntArrayExtra("Expense Notes")?: IntArray(7)
        val DateTextView = intent.getStringArrayExtra("Date")?: arrayOfNulls<String>(7)
        val Backbtn = findViewById<Button>(R.id.Backbtn)
        val detailsText = findViewById<TextView>(R.id.detailsText)

        val details = StringBuilder()
        details.append(
            String.format(
                "-15s%-15s%-15s%-15s\n",
                "Date", "Afternoon", "Morning", "Expense Notes"
            )
        )
        details.append("\n")
        for (i in 0 until 7) {
            val date = DateTextView[i] ?:"N/A"
            details.append(
                String.format(
                    "-15s%-15s%-15s%-15s\n",
                    date,
                    "${afternoonViewEditText[i]} ",
                    "${morningViewEditText}",
                    "${expenseViewEditText}"
                )
            )
        }
        detailsText.text = details.toString()
        Backbtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}


