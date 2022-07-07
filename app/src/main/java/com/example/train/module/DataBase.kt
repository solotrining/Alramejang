package com.example.train.module

import android.util.Log
import com.example.train.module.dto.PostDTO
import com.example.train.module.dto.UserDTO
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class DataBase {
    val db = Firebase.firestore

    fun putUser(User: UserDTO) {
        val user = hashMapOf(
            "id" to User.getEmail(),
            "password" to User.getPassWord(),
            "nickname" to User.getNickName()
        )
            db.collection("User").document(User.getEmail()).set(user)
                .addOnCompleteListener { Log.e("suc", "유저 데이터 기입 성공") }
                .addOnFailureListener { Log.e("Fail", it.message.toString()) }
    }

    fun writePost(post: PostDTO){
        val write = hashMapOf(
            "Writer" to post.getWriter(),
            "Title" to post.getTitle(),
            "Contents" to post.getContent()
        )

        db.collection("Write").document("${post.getWriter()}-${post.getTitle()}").set(write)
            .addOnCompleteListener { Log.e("Write","글 저장 성공!") }
            .addOnFailureListener { Log.e("Write","글 저장 실패...") }
    }


}

