package com.example.game1

import android.graphics.Color
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        var total = 0;

        val buttons = listOf<Button>(
            findViewById(R.id.num_10),
            findViewById(R.id.num_20),
            findViewById(R.id.num_30),
            findViewById(R.id.num_40),
            findViewById(R.id.num_50),
            findViewById(R.id.num_60),
            findViewById(R.id.num_70),
            findViewById(R.id.num_80),
            findViewById(R.id.num_90),
            findViewById(R.id.num_100)
        )


        val selectNumTextView = findViewById<TextView>(R.id.select_num)
        var selections = selectNumTextView.text.toString().toInt()
        val outputTextView = findViewById<TextView>(R.id.total)

        val displayNumTextView = findViewById<TextView>(R.id.display_num)

        val submitButton = findViewById<Button>(R.id.submit_button)

        val restartButton = findViewById<Button>(R.id.restart_button)

        for (button in buttons) {
            button.setOnClickListener {
                if (selections > 0) {
                    val number = button.text.toString().toInt()
                    total += number

                    selections--

                    button.setBackgroundColor(Color.parseColor("#FF5733"))

                    if (selections == 0) {

                        for (btn in buttons) {
                            btn.isEnabled = false
                        }
                    }
                }
            }
        }

        submitButton.setOnClickListener {

            if (total == displayNumTextView.text.toString().toInt()) {

                Toast.makeText(this, "correct total value", Toast.LENGTH_SHORT).show()
            } else {

                Toast.makeText(this, "Incorrect total value", Toast.LENGTH_SHORT).show()

            }
        }



        restartButton.setOnClickListener {
            // Reset total and selections
            total = 0
            selections = selectNumTextView.text.toString().toInt()

            // Reset output text view
            outputTextView.text = total.toString()

            // Enable all buttons
            for (button in buttons) {
                button.isEnabled = true
                button.setBackgroundColor(Color.TRANSPARENT) // Reset button color
            }
        }

    }
}




