package com.example.train.module.dto

data class UserDTO (
    private var email:String? = null,
    private var password:String? = null,
    private var nickname:String? = null
){
    fun getEmail():String{
        return this.email!!
    }
    fun getPassWord():String{
        return this.password!!
    }
    fun getNickName():String{
        return this.nickname!!
    }

    fun setEmail(email:String){
        this.email = email
    }

    fun setPassWord(password:String){
        this.password = password
    }

    fun setNickName(nickname:String){
        this.nickname = nickname
    }

}