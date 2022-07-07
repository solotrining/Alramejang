package com.example.train.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.train.R
import com.example.train.databinding.ActivitySignInBinding
import com.example.train.module.DataBase
import com.example.train.module.dto.UserDTO
import com.example.train.viewmodel.Sign_InModel

class SigninActivity : AppCompatActivity() {

    private lateinit var binding:ActivitySignInBinding
    private val model:Sign_InModel by viewModels()

    private val db:DataBase = DataBase()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_sign_in)
        binding.signInModel = model
        binding.lifecycleOwner = this

        binding.signInButton.setOnClickListener {

            if(binding.signInPw.text.toString() == binding.signInPwCheck.text.toString()) {
                model.createAccount(binding.signInId.text.toString(), binding.signInPw.text.toString(), this)
                val user: UserDTO = UserDTO()
                user.setNickName(binding.signInNickName.text.toString())
                user.setPassWord(binding.signInPw.text.toString())
                user.setEmail(binding.signInId.text.toString())
                db.putUser(user)

                finish()
            }
            else {
                Toast.makeText(
                    applicationContext,
                    "비밀번호가 다릅니다.",
                    Toast.LENGTH_SHORT
                ).show()

            }

        }
    }
}