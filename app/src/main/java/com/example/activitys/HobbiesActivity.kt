package com.example.activitys

import HobbiesViewModel
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.firebase.auth.FirebaseAuth

class HobbiesActivity : AppCompatActivity() {

    private val viewModel: HobbiesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hobies)

        val music = findViewById<CheckBox>(R.id.checkbox_music)
        val  sports = findViewById<CheckBox>(R.id.checkbox_sports)
        val comedy = findViewById<CheckBox>(R.id.checkbox_comedy)
        val theater = findViewById<CheckBox>(R.id.checkbox_theater)
        val userId = FirebaseAuth.getInstance().currentUser?.uid

        findViewById<Button>(R.id.button_save_hobbies).setOnClickListener {
            if (userId != null) {

            val selectedHobbies = listOf(
                "Music" to music.isChecked,
                "Sports" to sports.isChecked,
                "Comedy" to comedy.isChecked,
                "Theater" to theater.isChecked
            ).filter { it.second }.map { it.first }
            viewModel.saveUserHobbies(userId, selectedHobbies)
            } else {
                Toast.makeText(this, "restart and login", Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.saveSuccessful.observe(this) { success ->
            if (success) {
                startActivity(Intent(this, MapsActivity::class.java))
                finish()
            } else {
                Toast.makeText(
                    this,
                    "Reselect.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
