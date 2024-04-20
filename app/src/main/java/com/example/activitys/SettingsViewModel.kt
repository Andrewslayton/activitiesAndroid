package com.example.activitys.viewmodel

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore

class SettingsViewModel : ViewModel() {

    private val firebaseAuth = FirebaseAuth.getInstance()
    private val firestore = FirebaseFirestore.getInstance()
    private val _deleteSuccess = MutableLiveData<Boolean>()
    val deleteSuccess: LiveData<Boolean> = _deleteSuccess

    fun signOut() {
        firebaseAuth.signOut()
    }

    fun deleteAccount() {
        val user = firebaseAuth.currentUser
        if (user != null) {
            firestore.collection("users").document(user.uid)
                .delete()
                .addOnSuccessListener {
                    deleteUserAccount(user)
                }
                .addOnFailureListener {
                    _deleteSuccess.postValue(false)
                }
        } else {
            _deleteSuccess.postValue(false)
        }
    }

    private fun deleteUserAccount(user: FirebaseUser) {
        user.delete().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                _deleteSuccess.postValue(true)
            } else {
                _deleteSuccess.postValue(false)
                Toast.makeText(null, "Guess we cant delete your account", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
