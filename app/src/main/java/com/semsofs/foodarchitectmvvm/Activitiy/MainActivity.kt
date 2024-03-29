package com.semsofs.foodarchitectmvvm.Activitiy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.semsofs.foodarchitectmvvm.R


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val navigationController = Navigation.findNavController(this, R.id.home)

        NavigationUI.setupWithNavController(bottomNavigation,navigationController)

    }


}