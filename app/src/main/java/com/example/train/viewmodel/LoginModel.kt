package com.example.train.viewmodel


import androidx.lifecycle.ViewModel
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

class LoginModel : ViewModel() {
    companion object{
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("561673231649-f4kfvcu5k7gh3ll0ibok8von38afoush.apps.googleusercontent.com")
            .requestEmail()
            .build()
    }

    fun getgso(): GoogleSignInOptions {
        return gso
    }


}