package com.example.train.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.train.R
import com.example.train.databinding.ActivitySignInBinding
import com.example.train.module.DataBase
import com.example.train.module.dto.UserDTO
import com.example.train.viewmodel.SignInModel

class SignInActivity : AppCompatActivity() {

    private lateinit var binding:ActivitySignInBinding
    private val model:SignInModel by viewModels()

    private val db:DataBase = DataBase()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_sign_in)
        binding.signInModel = model
        binding.lifecycleOwner = this

        val pattern = Patterns.EMAIL_ADDRESS

        binding.signInButton.setOnClickListener {
            val id = binding.signInId.text.toString().trim()
            val pw = binding.signInPw.text.toString().trim()

            if(pw == binding.signInPwCheck.text.toString().trim()) {
                if(pattern.matcher(id).matches()) {
                    model.createAccount(id, pw, this)
                    val user: UserDTO = UserDTO()
                    user.setNickName(binding.signInNickName.text.toString().trim())
                    user.setPassWord(pw)
                    user.setEmail(id)
                    db.putUser(user)

                    finish()
                }
                else{ makeToast("아이디가 이메일 형식과 맞지 않습니다.") }
            }
            else { makeToast("비밀번호가 다릅니다.")
                println("pw = $pw")
                println("pwch = " + binding.signInPwCheck.text.toString().trim())
            }

        }
    }

    fun makeToast(text:String){
        Toast.makeText(
            applicationContext,
            text,
            Toast.LENGTH_SHORT
        ).show()
    }
}