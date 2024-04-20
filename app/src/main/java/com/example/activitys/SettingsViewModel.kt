package com.example.activitys.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
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
        user?.let {
            firestore.collection("users").document(it.uid).delete()
                .addOnSuccessListener {
                    user.delete()
                        .addOnCompleteListener { task ->
                            _deleteSuccess.postValue(task.isSuccessful)
                        }
                }
        }
    }
}
