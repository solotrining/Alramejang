package com.example.train.viewmodel


import android.app.Application
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import com.example.train.module.Auth
import com.example.train.module.dto.UserDTO
import com.example.train.view.MainActivity
import com.example.train.view.SetNickNameActivity
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore

class LoginModel : ViewModel() {

    companion object{
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("561673231649-f4kfvcu5k7gh3ll0ibok8von38afoush.apps.googleusercontent.com")
            .requestEmail()
            .build()
    }


    private val privateAuth:Auth = Auth()
    var auth = privateAuth.mAuth

    fun getgso(): GoogleSignInOptions {
        return gso
    }


    fun firebaseAuthWithGoogle(account : GoogleSignInAccount?, app : Application){
        val credential = GoogleAuthProvider.getCredential(account?.idToken,null)
        auth?.signInWithCredential(credential)
        FirebaseAuth.getInstance().signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if(task.isSuccessful){
                    saveUserDataToDatabase(task.result!!.user)
                    val intent = Intent(app.applicationContext,SetNickNameActivity::class.java)
                    ContextCompat.startActivity(app.applicationContext,intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK),null)
                }else
                    println("실패")
            }

    }


    fun loginNative(email: String, password: String, app:Application){

        if (email.isNotEmpty() && password.isNotEmpty()) {
            auth?.signInWithEmailAndPassword(email, password)
                ?.addOnCompleteListener {
                    if (it.isSuccessful) {
                        val intent = Intent(app,MainActivity::class.java)
                        ContextCompat.startActivity(app,intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK),null)
                        Log.e("suc", "성공")
                    } else {
                        Log.e("fail", "실패 " + it.exception.toString())
                        Toast.makeText(app.applicationContext,"존재하지 않는 계정입니다.",Toast.LENGTH_SHORT)
                            .show()
                    }
                }
        }

    }



    private fun saveUserDataToDatabase(user : FirebaseUser?){
        val email : String? = user?.email

        val userDTO = UserDTO()
        userDTO.setEmail(email!!)

        FirebaseFirestore.getInstance().collection("User").document(email).set(userDTO)
    }
}
