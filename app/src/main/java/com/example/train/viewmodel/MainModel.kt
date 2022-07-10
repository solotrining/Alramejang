package com.example.train.viewmodel

import androidx.lifecycle.ViewModel
import com.example.train.module.Auth

class MainModel : ViewModel() {
    var auth = Auth.getInstance()

}