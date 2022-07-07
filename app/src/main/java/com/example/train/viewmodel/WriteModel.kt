package com.example.train.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.example.train.module.Auth
import com.example.train.module.DataBase
import com.example.train.module.dto.PostDTO
import com.google.firebase.storage.FirebaseStorage


class WriteModel : ViewModel() {
    private val Auth: Auth = Auth()

    private val Database = DataBase()

    var auth = Auth.mAuth

    private var userName : String = "default 입니당"

    fun userGetName(email : String){

        Database.db.collection("User").document(email)
            .get()
            .addOnCompleteListener {
                setName(it.result["nickname"].toString())
            }
    }

    fun saveContent(title: String, Content: String){
        val post = PostDTO()
        post.setWriter(userName)
        post.setTitle(title)
        post.setContent(Content)
        Database.writePost(post)
    }


    private fun setName(username : String) {
        this.userName = username
    }

    fun saveImage(image : Uri){
        val storage = FirebaseStorage.getInstance()
        val filename = "rep$userName.png"

        val imgRef = storage.getReference("repPhoto/$filename")
        imgRef.putFile(image)

    }

}