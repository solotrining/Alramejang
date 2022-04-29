package com.example.train.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.train.R
import com.example.train.databinding.ActivityWriteBinding
import com.example.train.viewmodel.WriteModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class WriteActivity : AppCompatActivity() {

    lateinit var mbinding:ActivityWriteBinding

    val model:WriteModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mbinding = DataBindingUtil.setContentView(this,R.layout.activity_write)
        mbinding.writeModel = model
        mbinding.lifecycleOwner = this

        model.auth = Firebase.auth
        model.setDBName(model.auth!!.currentUser!!.email.toString())

        mbinding.WriteTitle.setText(model.getUserName())
        mbinding.WriteContents.setText("글 1번")

    }
}