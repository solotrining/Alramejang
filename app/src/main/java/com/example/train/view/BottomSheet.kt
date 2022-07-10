package com.example.train.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.train.R
import com.example.train.databinding.FragmentWriteBinding
import com.example.train.viewmodel.WriteModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheet : BottomSheetDialogFragment(), View.OnClickListener {
    private lateinit var binding : FragmentWriteBinding

    private var imageUri : Uri = Uri.EMPTY


    private lateinit var activityResult : ActivityResultLauncher<Intent>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_write, container, false)

        activityResult = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
            ActivityResultCallback { r ->
                if(r.resultCode == AppCompatActivity.RESULT_OK && r.data != null){
                    imageUri = r.data!!.data!!
                }
            }
        )


        binding.ImageSaveBtn.setOnClickListener {
            val model = WriteModel()
            model.saveImage(imageUri)
        }

        binding.repimage.setOnClickListener{
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/"
            activityResult.launch(intent)
        }


        return binding.root
    }

    override fun onClick(p0: View?) {
    }
}