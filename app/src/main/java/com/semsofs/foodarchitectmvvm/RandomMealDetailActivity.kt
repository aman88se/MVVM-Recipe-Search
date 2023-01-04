package com.semsofs.foodarchitectmvvm

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.semsofs.foodarchitectmvvm.Database.RecipeDatabase
import com.semsofs.foodarchitectmvvm.Fragments.HomeFragment
import com.semsofs.foodarchitectmvvm.databinding.ActivityRandomMealDetailBinding
import com.semsofs.foodarchitectmvvm.databinding.YoutubePlayerBinding
import com.semsofs.foodarchitectmvvm.model.Meal
import com.semsofs.foodarchitectmvvm.viewModel.RecipeViewModel
import com.semsofs.foodarchitectmvvm.viewModel.RecipeViewModelProvider


class RandomMealDetailActivity : AppCompatActivity() {

    private lateinit var mealId: String
    private lateinit var mealImage: String
    private lateinit var mealVideo: String
    private lateinit var strInstruction: String
    private lateinit var recipeViewModel: RecipeViewModel



    private lateinit var binding: YoutubePlayerBinding

    lateinit var youtubePlayerInit: YouTubePlayer.OnInitializedListener
    val API_KEY = "AIzaSyBKny9_AED8jotLZvQMI6DM8IcsSgdzAks"

    private val TAG = "RandomMealDetailActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = YoutubePlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Get data from HomeFragment
        val intent = intent
        mealImage = intent.getStringExtra(HomeFragment.MEALTHUMB)!!
        mealId = intent.getStringExtra(HomeFragment.MEALID)!!
//        mealName = intent.getStringExtra(HomeFragment.MEALNAME)!!
        strInstruction = intent.getStringExtra(HomeFragment.INSTRUCTIONS)!!
//        mealVideo = intent.getStringExtra(HomeFragment.MEALVIDEO)!!


//        val mealVideoId = mealVideo.substring(32)


        val recipeDatabase = RecipeDatabase.getInstance(this)
        val recipeViewModelProvider = RecipeViewModelProvider(recipeDatabase)
        recipeViewModel = ViewModelProvider(this,recipeViewModelProvider)[RecipeViewModel::class.java]

        recipeViewModel.getRecipeDetails(mealId)
        observeRecipeDetailLiveData()

        
        //change status bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val w = window
            w.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }






        //YoutubePlayerView

//        youtubePlayerInit = object : YouTubePlayer.OnInitializedListener{
//            override fun onInitializationSuccess(
//                p0: YouTubePlayer.Provider?,
//                p1: YouTubePlayer?,
//                p2: Boolean
//            ) {
//                p1?.loadVideo(mealVideoId)
//                p1?.play()
//            }
//
//            override fun onInitializationFailure(
//                p0: YouTubePlayer.Provider?,
//                p1: YouTubeInitializationResult?
//            ) {
//                Toast.makeText(applicationContext,"Error",Toast.LENGTH_SHORT).show()
//            }
//
//
//        }

        //Play youtube video
//        binding.youtubeApiView.initialize(API_KEY,youtubePlayerInit)

        binding.Instructions.text = strInstruction
        Glide.with(this).load(mealImage).into(binding.randomMealImage)

        onSaveButtonClick()


    }

    private fun onSaveButtonClick(){

        binding.addToFavoriteButton.setOnClickListener {

            recipeToSave?.let {

                recipeViewModel.insertRecipe(it)
                Toast.makeText(this,"Saved", Toast.LENGTH_SHORT).show()

            }

        }

    }

    private var recipeToSave: Meal? = null

    private fun observeRecipeDetailLiveData() {
        recipeViewModel.observeRecipeDetailLiveData().observe(this,object : Observer<Meal>{
            override fun onChanged(t: Meal?) {
                val meal = t
                recipeToSave = meal
            }

        })
    }






}