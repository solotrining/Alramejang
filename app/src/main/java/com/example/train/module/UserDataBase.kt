package com.example.train.module

import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class UserDataBase {
    val db = Firebase.firestore

    fun putUser(name : String, id : String,pw : String) {
        val user = hashMapOf(
            "Id" to id,
            "PassWord" to pw,
            "NickName" to name
        )
            db.collection("User").document(id).set(user)
                .addOnCompleteListener { Log.e("suc", "유저 데이터 기입 성공") }
                .addOnFailureListener { Log.e("Fail", it.message.toString()) }
    }

}

