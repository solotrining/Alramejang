package com.example.train.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.train.module.Auth
import com.example.train.module.DataBase
import com.example.train.module.UserDTO
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class WriteModel : ViewModel() {
    private val Auth: Auth = Auth()

    private val db = FirebaseFirestore.getInstance()

    var auth = Auth.mAuth

    private var UserName : String = ""

    fun UsergetName(email : String) : String{
        db.collection("User").document(email)
            .get()
            .addOnCompleteListener {
                SetName(it.result["NickName"] as String)
            }
        return GetName()
    }


    private fun SetName(username:String){
        this.UserName = username
    }

    private fun GetName() : String{
        return UserName
    }

}