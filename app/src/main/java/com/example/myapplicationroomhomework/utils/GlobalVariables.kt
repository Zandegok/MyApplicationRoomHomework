package com.example.myapplicationroomhomework.utils

import android.content.Context
import com.example.myapplicationroomhomework.model.AppDatabase

class GlobalVariables private constructor() {
    companion object {
        val instance = GlobalVariables()
    }

    lateinit var appDatabase: AppDatabase
    lateinit var applicationContext: Context
}