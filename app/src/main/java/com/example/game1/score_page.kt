package com.example.game1

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.widget.Button
import android.widget.TextView

class score_page : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.score_activity)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        sharedPreferences = getSharedPreferences("GamePreferences", Context.MODE_PRIVATE)
        val gameResultTextView = findViewById<TextView>(R.id.scorelist)

        // Check if "game_result" exists in SharedPreferences
        val gameResult = sharedPreferences.getString("game_result", "")

        // Check if "game_result" is not empty and display it
        if (!gameResult.isNullOrEmpty()) {
            gameResultTextView.text = "$gameResult"
            //Toast.makeText(this, "Previous game result: $gameResult", Toast.LENGTH_SHORT).show()
            // Optionally, you can clear the "game_result" after reading it
//            val editor = sharedPreferences.edit()
//            editor.remove("game_result")
//            editor.apply()
        }

        val backButton = findViewById<Button>(R.id.bck_button)
        backButton.setOnClickListener {
            var intent = Intent(this,mainmenu::class.java)
            startActivity(intent)
            finish()

        }
    }
}