package com.example.train.view

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.train.R
import com.example.train.databinding.ActivityWriteBinding
import com.example.train.viewmodel.WriteModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlin.system.exitProcess

class WriteActivity : AppCompatActivity() {

    lateinit var mbinding:ActivityWriteBinding

    val model:WriteModel by viewModels()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model.userGetName(Firebase.auth.currentUser!!.email.toString())

        mbinding = DataBindingUtil.setContentView(this, R.layout.activity_write)
        mbinding.writeModel = model
        mbinding.lifecycleOwner = this

        mbinding.WriteWriteBtn.setOnClickListener {

            if(mbinding.WriteTitle.text.isNotEmpty()
                && mbinding.WriteContents.text.isNotEmpty()) {

                    model.saveContent(mbinding.WriteTitle.text.toString(),
                        mbinding.WriteContents.text.toString())

                // 바텀 시트 작성
                val bottomSheetDialog = BottomSheet()
                bottomSheetDialog.show(supportFragmentManager, bottomSheetDialog.tag)

            }else{
                Toast.makeText(this,"제목 또는 내용을 작성해 주세요.",Toast.LENGTH_SHORT)
                    .show()
            }

        }

    }

}