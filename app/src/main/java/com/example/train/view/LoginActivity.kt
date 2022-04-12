package com.example.train.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.train.R
import com.example.train.databinding.ActivityLoginBinding
import com.example.train.module.Auth
import com.example.train.module.UserDTO
import com.example.train.viewmodel.LoginModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore


class LoginActivity : AppCompatActivity() {

    private lateinit var login: ActivityLoginBinding
    private val model:LoginModel by viewModels()

    //Google Login
    private var mGoogleSignInClient: GoogleSignInClient? = null

    private lateinit var activityLauncher : ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        login = DataBindingUtil.setContentView(this,R.layout.activity_login)
        login.loginViewModel = model
        login.lifecycleOwner = this

        model.auth = FirebaseAuth.getInstance()

        mGoogleSignInClient = GoogleSignIn.getClient(this, model.getgso())

        login.toSignIn.setOnClickListener {
            val goSignIn = Intent(applicationContext, SigninActivity::class.java)
            startActivity(goSignIn)
        }

        activityLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
                if (result.resultCode == RESULT_OK) {
                    val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                    try {
                        val account = task.getResult(ApiException::class.java)!!
                        model.firebaseAuthWithGoogle(account)
                        Log.e("GoogleLogin", "fireBaseAuthWithGoogle:" + account.id)
                    } catch (e: ApiException) {
                        Log.e("GoogleLogin", "Google sign in failed" + e.message)
                    }
                }

            }

        //구글 아이디로 이미 로그인 했다면, 바로 로그인
        if (model.auth!!.currentUser != null) {
            val intent = Intent(application, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        login.GoogleLogin.setOnClickListener {
            activityLauncher.launch(mGoogleSignInClient!!.signInIntent)
            finish()
            startActivity(Intent(this, MainActivity::class.java))
        }

    }

}