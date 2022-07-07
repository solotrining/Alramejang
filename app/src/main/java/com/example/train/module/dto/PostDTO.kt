package com.example.train.module.dto

data class PostDTO(
    private var writer : String? = null,
    private var title : String? = null,
    private var content : String? = null
){
    fun getWriter():String{
        return writer!!
    }

    fun getTitle():String{
        return title!!
    }

    fun getContent():String{
        return content!!
    }

    fun setWriter(writer:String){
        this.writer = writer
    }

    fun setTitle(title:String){
        this.title = title
    }

    fun setContent(content:String){
        this.content = content
    }
}