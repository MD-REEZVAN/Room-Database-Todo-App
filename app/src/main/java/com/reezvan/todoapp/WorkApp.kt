package com.reezvan.todoapp

import android.app.Application

class WorkApp: Application() {
    override fun onCreate() {
        super.onCreate()
        WorkGraph.create(this)
    }
}