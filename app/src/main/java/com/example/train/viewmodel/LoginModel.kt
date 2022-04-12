package com.example.train.viewmodel


import androidx.lifecycle.ViewModel
import com.example.train.module.Auth
import com.example.train.module.UserDTO
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore

class LoginModel : ViewModel() {

    private val privateAuth:Auth = Auth()
    var auth = privateAuth.mAuth
    companion object{
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("561673231649-f4kfvcu5k7gh3ll0ibok8von38afoush.apps.googleusercontent.com")
            .requestEmail()
            .build()
    }

    fun getgso(): GoogleSignInOptions {
        return gso
    }

    fun firebaseAuthWithGoogle(account : GoogleSignInAccount?){
        val credential = GoogleAuthProvider.getCredential(account?.idToken,null)
        auth?.signInWithCredential(credential)
        FirebaseAuth.getInstance().signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if(task.isSuccessful){
                    saveUserDataToDatabase(task.result!!.user)
                }else
                    println("실패")
            }

    }

    fun saveUserDataToDatabase(user : FirebaseUser?){
        val email : String? = user?.email
        val uid : String? = user?.uid

        val userDTO = UserDTO()
        userDTO.email = email

        FirebaseFirestore.getInstance().collection("users").document(uid!!).set(userDTO)
    }


}