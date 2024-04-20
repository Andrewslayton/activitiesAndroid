package com.example.activitys

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.activitys.viewmodel.SettingsViewModel

class SettingsActivity : AppCompatActivity() {

    private val viewModel: SettingsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val signOutButton: Button = findViewById(R.id.signOutButton)
        signOutButton.setOnClickListener {
            viewModel.signOut()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        val deleteAccountButton: Button = findViewById(R.id.deleteAccountButton)
        deleteAccountButton.setOnClickListener {
            viewModel.deleteAccount()
        }

        viewModel.deleteSuccess.observe(this, Observer { success ->
            if (success) {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Guess we cant delete your account", Toast.LENGTH_SHORT).show()
            }
        })
    }
}

