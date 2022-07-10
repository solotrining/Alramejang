package com.example.train.module

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Auth private constructor(){
    companion object{
        private var mAuth: FirebaseAuth? = null

        fun getInstance() : FirebaseAuth? {
            if (mAuth == null) mAuth = Firebase.auth
            return mAuth
        }
    }
}