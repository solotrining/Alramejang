package com.example.train.view

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class WriteActivity : AppCompatActivity() {

    lateinit var mbinding:ActivityWriteBinding

    val model:WriteModel by viewModels()

    private lateinit var imageUri : Uri

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
                val bottomSheet = BottomSheet()
                bottomSheet.show(supportFragmentManager, bottomSheet.tag)


            }else{
                Toast.makeText(this,"제목 또는 내용을 작성해 주세요.",Toast.LENGTH_SHORT)
                    .show()
            }
        }

        val activityResult : ActivityResultLauncher<Intent> = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
            ActivityResultCallback<ActivityResult>() { r ->
                if(r.resultCode == RESULT_OK && r.data != null){
                    imageUri = r.data!!.data!!
                }
            }
        )
        val imagebtn = findViewById<Button>(R.id.ImageSaveBtn)
        val image = findViewById<ImageView>(R.id.repimage)

        image.setOnClickListener{

            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/"
            activityResult.launch(intent)

        }

        imagebtn.setOnClickListener {
            model.saveImage(imageUri,application)
        }


    }

}