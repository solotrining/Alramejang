package com.example.train.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.train.R
import com.example.train.databinding.ActivityLoginBinding
import com.example.train.viewmodel.LoginModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.firebase.auth.FirebaseAuth


class LoginActivity : AppCompatActivity() {

    private lateinit var login: ActivityLoginBinding
    private val model:LoginModel by viewModels()

    //Google Login
    private var mAuth: FirebaseAuth? = null
    private var mGoogleSignInClient: GoogleSignInClient? = null
    private var RC_SIGN_IN = 9001
    private var signInButton: SignInButton? = null

    val loginModel:LoginModel? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        login = DataBindingUtil.setContentView(this,R.layout.activity_login)
        login.loginViewModel = model
        login.lifecycleOwner = this

        signInButton = findViewById(R.id.Google_Login);
        mAuth = FirebaseAuth.getInstance();

        mGoogleSignInClient = GoogleSignIn.getClient(this, model.getgso())

        //구글 아이디로 이미 로그인 했다면, 바로 로그인
        if (mAuth!!.currentUser != null) {
            val intent = Intent(application, MainActivity::class.java)
            startActivity(intent)
            finish()
        }


    }
}