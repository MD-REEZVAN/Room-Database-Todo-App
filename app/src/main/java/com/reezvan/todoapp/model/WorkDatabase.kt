package com.reezvan.todoapp.model

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [WorkModel::class],
    version = 1,
    exportSchema = false
    )
abstract class WorkDatabase: RoomDatabase() {
    abstract fun dao(): WorkDao
}