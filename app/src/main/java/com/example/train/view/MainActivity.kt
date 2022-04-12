package com.example.train.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.train.R
import com.example.train.databinding.ActivityMainBinding
import com.example.train.viewmodel.MainModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var mbinding:ActivityMainBinding
    private val model:MainModel by viewModels()

    private var auth = model.auth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mbinding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        mbinding.mainViewModel = model
        mbinding.lifecycleOwner = this

        auth = Firebase.auth

        mbinding.Logout.setOnClickListener {
            val intent = Intent(applicationContext,LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            auth?.signOut()
            finish()
        }

        mbinding.Withdrawal.setOnClickListener {
            val intent = Intent(applicationContext,LoginActivity::class.java)
            startActivity(intent)
            FirebaseAuth.getInstance().currentUser!!.delete()
        }
    }
}