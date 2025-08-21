package com.reezvan.todoapp

import android.content.Context
import androidx.room.Room
import com.reezvan.todoapp.model.WorkDatabase
import com.reezvan.todoapp.model.WorkRepository

object WorkGraph {

    lateinit var database: WorkDatabase

    val workRepo by lazy{
        WorkRepository(dao = database.dao())
    }


    fun create(context: Context){
        database= Room.databaseBuilder(context = context, klass = WorkDatabase::class.java, name = "work_db")
            .build()
    }



}