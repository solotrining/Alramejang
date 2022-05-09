package com.example.train.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.train.R
import com.example.train.databinding.ActivitySetnicknameBinding
import com.example.train.viewmodel.SetNIckNameModel

class SetNickNameActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySetnicknameBinding
    private val model : SetNIckNameModel by viewModels()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_setnickname)
        binding.lifecycleOwner = this
        binding.model = model

        binding.GoogleNickNameNextBtn.setOnClickListener {
            model.GoogleSetNickName(binding.GoogleNickName.text.toString(),this.application)
            finish()
        }
    }
}