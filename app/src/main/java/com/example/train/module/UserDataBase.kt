package com.example.train.module

import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class UserDataBase {
    val db = Firebase.firestore

    fun putUser(name: String, id: String) {
        val user = hashMapOf(
            "Id" to id,
            "NickName" to name
        )
        db.collection("User").document(id).set(user)
            .addOnCompleteListener { Log.e("suc", "유저 데이터 기입 성공") }
            .addOnFailureListener { Log.e("Fail", it.message.toString()) }

        Log.e("Finalname",getUserName(id))
    }

    fun getUserName(email:String):String {
        var name:String = "name"
        db.collection("User").document(email).get()
            .addOnCompleteListener {
            name = it.result.get("NickName").toString()
                Log.e("name",name)
        }

        return name
    }
}