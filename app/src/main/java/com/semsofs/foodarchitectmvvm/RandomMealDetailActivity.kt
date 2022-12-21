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
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.semsofs.foodarchitectmvvm.Fragments.HomeFragment
import com.semsofs.foodarchitectmvvm.databinding.ActivityRandomMealDetailBinding


class RandomMealDetailActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {

    private lateinit var mealName: String
    private lateinit var mealImage: String
    private lateinit var mealVideo: String
    private lateinit var binding: ActivityRandomMealDetailBinding

    lateinit var youtubePlayerInit: YouTubePlayer.OnInitializedListener
    val API_KEY = "AIzaSyBKny9_AED8jotLZvQMI6DM8IcsSgdzAks"

    private val TAG = "RandomMealDetailActivity"

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


        //Get data from HomeFragment
        val intent = intent
//        mealImage = intent.getStringExtra(HomeFragment.MEALTHUMB)!!
//        mealName = intent.getStringExtra(HomeFragment.MEALNAME)!!
        mealVideo = intent.getStringExtra(HomeFragment.MEALVIDEO)!!

        val mealVideoId = mealVideo.substring(32)


        //YoutubePlayerView

        binding.youtubePlayer.setOnClickListener {
            binding.youtubePlayer.initialize(API_KEY, this)
        }





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

    override fun onInitializationSuccess(p0: YouTubePlayer.Provider?, p1: YouTubePlayer?,
                                         p2: Boolean
    ) {
        Log.d(TAG, "onInitializationSuccess: provider is ${p0?.javaClass}")
        Log.d(TAG, "onInitializationSuccess: youTubePlayer is ${p1?.javaClass}")
        Toast.makeText(this, "Initialized Youtube Player successfully", Toast.LENGTH_SHORT).show()
        val mealVideoId = mealVideo.substring(32)

        p1?.setPlayerStateChangeListener(playerStateChangeListener)
        p1?.setPlaybackEventListener(playbackEventListener)

        if (!p2) {
            p1?.cueVideo(mealVideoId)
        }


    }

    override fun onInitializationFailure(
        p0: YouTubePlayer.Provider?,
        p1: YouTubeInitializationResult?
    ) {
        val REQUEST_CODE = 0

        if (p1?.isUserRecoverableError == true) {
            p1.getErrorDialog(this, REQUEST_CODE).show()
        } else {
            val errorMessage = "There was an error initializing the YoutubePlayer ($p1)"
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
        }
    }

    private val playbackEventListener = object: YouTubePlayer.PlaybackEventListener {
        override fun onSeekTo(p0: Int) {
        }

        override fun onBuffering(p0: Boolean) {
        }

        override fun onPlaying() {
            Toast.makeText(this@RandomMealDetailActivity, "Good, video is playing ok", Toast.LENGTH_SHORT).show()
        }

        override fun onStopped() {
            Toast.makeText(this@RandomMealDetailActivity, "Video has stopped", Toast.LENGTH_SHORT).show()
        }

        override fun onPaused() {
            Toast.makeText(this@RandomMealDetailActivity, "Video has paused", Toast.LENGTH_SHORT).show()
        }
    }

    private val playerStateChangeListener = object: YouTubePlayer.PlayerStateChangeListener {
        override fun onAdStarted() {
            Toast.makeText(this@RandomMealDetailActivity, "Click Ad now, make the video creator rich!", Toast.LENGTH_SHORT).show()
        }

        override fun onLoading() {
        }

        override fun onVideoStarted() {
            Toast.makeText(this@RandomMealDetailActivity, "Video has started", Toast.LENGTH_SHORT).show()
        }

        override fun onLoaded(p0: String?) {
        }

        override fun onVideoEnded() {
            Toast.makeText(this@RandomMealDetailActivity, "Congratulations! You've completed another video.", Toast.LENGTH_SHORT).show()
        }

        override fun onError(p0: YouTubePlayer.ErrorReason?) {
        }
    }



}