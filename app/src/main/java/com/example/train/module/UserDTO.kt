package com.example.train.module

data class UserDTO (
    var email:String? = null,
    var password:String? = null,
    var channel:String? = null,
    var Nickname:String? = null
){
    fun getemail(): String? {
        return this.email
    }

    fun getname(): String? {
        return this.Nickname
    }

    fun getpw(): String? {
        return this.password
    }

    fun setemail(email:String) {
        this.email = email
    }

    fun setname(name:String){
        this.Nickname = name
    }

    fun setpw(pw:String) {
        this.password = pw
    }
}