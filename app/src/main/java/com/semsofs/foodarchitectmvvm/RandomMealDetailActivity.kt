package com.semsofs.foodarchitectmvvm

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.semsofs.foodarchitectmvvm.Fragments.HomeFragment
import com.semsofs.foodarchitectmvvm.databinding.ActivityRandomMealDetailBinding

class RandomMealDetailActivity : AppCompatActivity() {

    private lateinit var mealName: String
    private lateinit var mealImage: String
//    private lateinit var mealInst: String
    private lateinit var binding: ActivityRandomMealDetailBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRandomMealDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        //change status bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val w = window
            w.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }

//        val windowInsetsController =
//            WindowCompat.getInsetsController(window, window.decorView) ?: return
//        windowInsetsController.systemBarsBehavior =
//            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
//        windowInsetsController.hide(WindowInsetsCompat.Type.statusBars())

        val intent = intent
        mealImage = intent.getStringExtra(HomeFragment.MEALTHUMB)!!
        mealName = intent.getStringExtra(HomeFragment.MEALNAME)!!
//        mealInst = intent.getStringExtra(HomeFragment.MEALINSTRUCTION)!!
//        mealCategory = intent.getStringExtra(HomeFragment.MEALCATEGORY)!!

        binding.randomMealName.text = mealName
//        binding.randomMealCategory.text = mealCategory
//        binding.randomMealInstruction.text = mealInst
        Glide.with(this).load(mealImage).into(binding.randomMealImageDetail)

        var click = 2
        binding.addToFavoriteButton.setOnClickListener {

            if (click % 2 == 0) {
                binding.addToFavoriteButton.setImageResource(R.drawable.baseline_bookmark)
                Snackbar.make(it,"Added to Favorite",Snackbar.LENGTH_SHORT).show()
                click++

            }else if(click % 2 != 0){
                binding.addToFavoriteButton.setImageResource(R.drawable.bookmark_border)
                Snackbar.make(it,"Removed from Favorite",Snackbar.LENGTH_SHORT).show()
                click++

            }

        }

    }


}