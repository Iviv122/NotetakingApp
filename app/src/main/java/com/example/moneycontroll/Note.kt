package com.example.moneycontroll

import java.time.LocalDate
import java.time.LocalDateTime
import java.util.Date

public class Note(nTitle : String, nDesription : String) {
    private var title : String
    private var description : String
    private var createDate : LocalDateTime

    init {
        title = nTitle
        description = nDesription
        createDate = LocalDateTime.now()
    }
    public fun ChangeTitle(nTitle: String){
        title = nTitle
    }
    public fun ChangeDiscription(nDisc: String){
        description = nDisc
    }
    public fun getTitle() : String{
        return title
    }
    public fun getDesc() : String{
        return description
    }
    public fun getDate() : String{
        return createDate.toString()
    }
}