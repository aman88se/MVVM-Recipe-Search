package com.semsofs.foodarchitectmvvm

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.semsofs.foodarchitectmvvm.Fragments.HomeFragment
import com.semsofs.foodarchitectmvvm.databinding.ActivityRandomMealDetailBinding
import com.semsofs.foodarchitectmvvm.databinding.YoutubePlayerBinding


class RandomMealDetailActivity : YouTubeBaseActivity(){

    private lateinit var mealName: String
    private lateinit var mealImage: String
    private lateinit var mealVideo: String
    private lateinit var strInstruction: String

    private lateinit var binding: YoutubePlayerBinding

    lateinit var youtubePlayerInit: YouTubePlayer.OnInitializedListener
    val API_KEY = "AIzaSyBKny9_AED8jotLZvQMI6DM8IcsSgdzAks"

    private val TAG = "RandomMealDetailActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = YoutubePlayerBinding.inflate(layoutInflater)
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


        //Get data from HomeFragment
        val intent = intent
//        mealImage = intent.getStringExtra(HomeFragment.MEALTHUMB)!!
//        mealName = intent.getStringExtra(HomeFragment.MEALNAME)!!
//        strInstruction = intent.getStringExtra(HomeFragment.INSTRUCTIONS)!!
        mealVideo = intent.getStringExtra(HomeFragment.MEALVIDEO)!!


        val mealVideoId = mealVideo.substring(32)


        //YoutubePlayerView

        youtubePlayerInit = object : YouTubePlayer.OnInitializedListener{
            override fun onInitializationSuccess(
                p0: YouTubePlayer.Provider?,
                p1: YouTubePlayer?,
                p2: Boolean
            ) {
                p1?.loadVideo(mealVideoId)
                p1?.play()
            }

            override fun onInitializationFailure(
                p0: YouTubePlayer.Provider?,
                p1: YouTubeInitializationResult?
            ) {
                Toast.makeText(applicationContext,"Error",Toast.LENGTH_SHORT).show()
            }


        }

        //Play youtube video
        binding.youtubeApiView.initialize(API_KEY,youtubePlayerInit)

//        binding.Instructions.text = strInstruction






//        binding.randomMealName.text = mealVideoId
//        Glide.with(this).load(mealImage).into(binding.randomMealImageDetail)


//        var click = 2
//        binding.addToFavoriteButton.setOnClickListener {
//
//            if (click % 2 == 0) {
//                binding.addToFavoriteButton.setImageResource(R.drawable.baseline_bookmark)
//                Snackbar.make(it,"Added to Favorite",Snackbar.LENGTH_SHORT).show()
//                click++
//
//            }else if(click % 2 != 0){
//                binding.addToFavoriteButton.setImageResource(R.drawable.bookmark_border)
//                Snackbar.make(it,"Removed from Favorite",Snackbar.LENGTH_SHORT).show()
//                click++
//
//            }
//
//        }

    }

}