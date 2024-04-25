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
import android.os.CountDownTimer
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class game : AppCompatActivity() {

    private var countDownTimer: CountDownTimer? = null
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        sharedPreferences = getSharedPreferences("GamePreferences", Context.MODE_PRIVATE)

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
        val timerTextView = findViewById<TextView>(R.id.timerTextView)
        val displayNumTextView = findViewById<TextView>(R.id.display_num)
        val submitButton = findViewById<Button>(R.id.submit_button)
        val restartButton = findViewById<Button>(R.id.restart_button)
        val backButton = findViewById<Button>(R.id.bck_button)


        submitButton.setOnClickListener {

            // Stop the CountDownTimer
            countDownTimer?.cancel()

            if (total == displayNumTextView.text.toString().toInt()) {

                Toast.makeText(this, "correct total value", Toast.LENGTH_SHORT).show()

                // Retrieve existing "game_result" data
                val existingData = sharedPreferences.getString("game_result", "")

                // Create new game result entry with current date and time
                val currentDateTime = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
                val newGameResult = "$currentDateTime - win"

                // Combine new data with existing data, separated by newline
                val updatedGameResult = if (existingData.isNullOrEmpty()) {
                    newGameResult
                } else {
                    "$newGameResult\n$existingData"
                }

                // Save updated game result data to SharedPreferences
                val editor = sharedPreferences.edit()
                editor.putString("game_result", updatedGameResult)
                editor.apply()

                restartButton.performClick()

            } else {

                Toast.makeText(this, "Incorrect total value", Toast.LENGTH_SHORT).show()

            }
        }

        backButton.setOnClickListener {
            // Stop the CountDownTimer
            countDownTimer?.cancel()

            var intent = Intent(this,mainmenu::class.java)
            startActivity(intent)
            finish()

        }

        val TotalValuNumbers = arrayOf(150, 20, 110, 130, 70, 60, 120, 40, 30, 10)
        val Selected_numbers = arrayOf(2, 4, 3)
        val Button_numbers = arrayOf(10, 20, 30, 40, 50, 60, 70, 80, 90, 100)

        restartButton.setOnClickListener {
            // Stop the CountDownTimer
            countDownTimer?.cancel()
            // Reset total and selections
            total = 0

            // Reset output text view
            outputTextView.text = total.toString()

            // Enable all buttons
            for (button in buttons) {
                button.isEnabled = true
                button.setBackgroundColor(Color.parseColor("#4CAF50")) // Reset button color
            }

            val randomIndex = (0 until TotalValuNumbers.size).random()

            // Get the randomly selected number
            val randomNumber = TotalValuNumbers[randomIndex]

            // Set the text of displayNumTextView to the randomly selected number
            displayNumTextView.text = randomNumber.toString()

             val randomIndex2 = (0 until Selected_numbers.size).random()

            // Get the randomly selected number
              val randomNumber2 = Selected_numbers[randomIndex2]

            // Set the text of displayNumTextView to the randomly selected number
             selectNumTextView.text = randomNumber2.toString()
             selections = selectNumTextView.text.toString().toInt()

            // Shuffle the Button_numbers array
            Button_numbers.shuffle()

            // Assign shuffled numbers to buttons
            for ((index, button) in buttons.withIndex()) {
                button.text = Button_numbers[index].toString()
            }

            // Start the countdown timer
            countDownTimer = object : CountDownTimer(21000, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    // Timer is ticking down
                    timerTextView.text = "Time Remaining: ${millisUntilFinished / 1000}"
                }

                override fun onFinish() {
                    // Timer finished, automatically click submitButton
                    Toast.makeText(this@game,"Time is over", Toast.LENGTH_SHORT).show()
                    submitButton.performClick()
                    restartButton.performClick()

                }
            }.start()
        }

        restartButton.performClick()


        for (button in buttons) {
            button.setOnClickListener {

                if (selections > 0) {
                    val number = button.text.toString().toInt()
                    total += number
                    outputTextView.text = total.toString()
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




    }
}



