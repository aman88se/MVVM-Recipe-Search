package com.semsofs.foodarchitectmvvm

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.bumptech.glide.Glide
import com.semsofs.foodarchitectmvvm.Fragments.HomeFragment
import com.semsofs.foodarchitectmvvm.databinding.ActivityRandomMealDetailBinding

class RandomMealDetailActivity : AppCompatActivity() {

    private lateinit var mealImage: String
    private lateinit var binding: ActivityRandomMealDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRandomMealDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val w = window
            w.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }

        val intent = intent
        mealImage = intent.getStringExtra(HomeFragment.MEALTHUMB)!!

        Glide.with(this).load(mealImage).into(binding.randomMealImageDetail)

    }


}