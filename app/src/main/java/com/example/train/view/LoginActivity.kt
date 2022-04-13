package com.example.train.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.train.R
import com.example.train.databinding.ActivityLoginBinding
import com.example.train.viewmodel.LoginModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


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

        //이미 로그인 했다면, 바로 로그인
        if (model.auth!!.currentUser != null) {
            goMain(model.auth?.currentUser)
        }

        login.GoogleLogin.setOnClickListener {
            activityLauncher.launch(mGoogleSignInClient!!.signInIntent)
            goMain(model.auth?.currentUser)
        }

        login.loginButton.setOnClickListener {
            model.loginNative(login.loginId.text.toString(),login.loginPw.text.toString())
            if(model.getResult() == "suc"){
                goMain(model.auth?.currentUser)
            }else{
                Toast.makeText(baseContext,"로그인 실패",Toast.LENGTH_SHORT).show()
            }
        }

    }


    fun goMain(user: FirebaseUser?){
        if(user != null) {
            val intent = Intent(application, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}