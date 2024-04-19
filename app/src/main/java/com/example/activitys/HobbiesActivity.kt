package com.example.activitys

import HobbiesViewModel
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class HobbiesActivity : AppCompatActivity() {

    private val viewModel: HobbiesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hobies)

        val reading = findViewById<CheckBox>(R.id.checkbox_reading)
        val gaming = findViewById<CheckBox>(R.id.checkbox_gaming)
        val hiking = findViewById<CheckBox>(R.id.checkbox_hiking)
        val cooking = findViewById<CheckBox>(R.id.checkbox_cooking)
        val userId = FirebaseAuth.getInstance().currentUser?.uid

        findViewById<Button>(R.id.button_save_hobbies).setOnClickListener {
            if (userId != null) {

            val selectedHobbies = listOf(
                "Reading" to reading.isChecked,
                "Gaming" to gaming.isChecked,
                "Hiking" to hiking.isChecked,
                "Cooking" to cooking.isChecked
            ).filter { it.second }.map { it.first }
            viewModel.saveUserHobbies(userId, selectedHobbies)
            } else {
                Toast.makeText(this, "dont know how you did this but restart and login", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
