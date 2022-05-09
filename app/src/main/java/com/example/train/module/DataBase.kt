package com.example.train.module

import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class DataBase {
    val db = Firebase.firestore

    fun putUser(name : String, id : String,pw : String) {
        val user = hashMapOf(
            "id" to id,
            "password" to pw,
            "nickname" to name
        )
            db.collection("User").document(id).set(user)
                .addOnCompleteListener { Log.e("suc", "유저 데이터 기입 성공") }
                .addOnFailureListener { Log.e("Fail", it.message.toString()) }
    }

    fun writePost(Writer : String, Title : String, Contents : String){
        val write = hashMapOf(
            "Writer" to Writer,
            "Title" to Title,
            "Contents" to Contents
        )

        db.collection("Write").document("$Writer-$Title").set(write)
            .addOnCompleteListener { Log.e("Write","글 저장 성공!") }
            .addOnFailureListener { Log.e("Write","글 저장 실패...") }
    }




}

