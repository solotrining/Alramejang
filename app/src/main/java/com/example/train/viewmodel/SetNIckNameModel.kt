package com.example.train.viewmodel

import android.app.Application
import android.content.Intent
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import com.example.train.module.Auth
import com.example.train.view.MainActivity
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class SetNIckNameModel : ViewModel() {

    private val privateAuth: Auth = Auth()
    var auth = privateAuth.mAuth

    fun GoogleSetNickName(Nickname : String, app : Application){
        auth = Firebase.auth

        FirebaseFirestore.getInstance().collection("User")
            .document(auth!!.currentUser!!.email.toString())
            .update("nickname",Nickname)

        val intent = Intent(app.applicationContext,MainActivity::class.java)
        ContextCompat.startActivity(app.applicationContext,intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK),null)
    }
}