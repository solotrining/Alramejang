package com.example.train.viewmodel

import androidx.lifecycle.ViewModel
import com.example.train.module.Auth
import com.example.train.module.UserDTO
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class WriteModel : ViewModel() {
    private val Auth: Auth = Auth()

    var auth = Auth.mAuth

    val db = Firebase.firestore

    val user:UserDTO = UserDTO()

    fun setDBName(id:String){

        db.collection("User").document(id).get().addOnCompleteListener {
           user.setname(it.result.get("NickName").toString())
        }
    }
    fun getUserName():String{
        return user.getname()!!
    }
}