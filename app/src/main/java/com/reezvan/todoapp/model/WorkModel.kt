package com.reezvan.todoapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "work_table")
data class WorkModel(
    @PrimaryKey(autoGenerate = true)
    val id:Long=0L,
    @ColumnInfo(name = "name")
    val workName:String,
    @ColumnInfo(name = "deadline")
    val deadline:String,
    @ColumnInfo(name = "priority")
    val priority: String,
    @ColumnInfo(name = "desc")
    val description: String,
    @ColumnInfo(defaultValue = "CURRENT_TIMESTAMP")
    val createdTime: String,
)
