package com.example.myapplicationroomhomework.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplicationroomhomework.model.AppDatabase
import com.example.myapplicationroomhomework.utils.GlobalVariables
import com.example.myapplicationroomhomework.controller.MainActivityViewModel
import com.example.myapplicationroomhomework.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val globalVariables = GlobalVariables.instance
        globalVariables.appDatabase = AppDatabase.getInstance(applicationContext)
        globalVariables.applicationContext = applicationContext

        var binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var mainActivityViewModel = MainActivityViewModel()
        binding.viewModel = mainActivityViewModel
    }
}