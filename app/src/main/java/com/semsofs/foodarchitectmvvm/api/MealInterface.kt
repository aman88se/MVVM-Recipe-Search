package com.semsofs.foodarchitectmvvm.api

import com.semsofs.foodarchitectmvvm.Fragments.HomeFragment
import com.semsofs.foodarchitectmvvm.databinding.FragmentHomeBinding
import com.semsofs.foodarchitectmvvm.model.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface MealInterface {


    @GET("random.php")
    fun getAllMeals(): Call<RandomMealList>

    @GET("categories.php")
    fun getAllCategories() : Call<CategoryList>

    @GET("lookup.php")
    fun getRecipeDetails(@Query("i") id: String) : Call<RandomMealList>

    @GET("filter.php?")
    fun getPopularMeals(@Query("c") Category: String): Call<PopularMealList>

    @GET("filter.php")
    fun getCategoryList(@Query("c") Category: String): Call<PopularMealList>

    companion object {
        var retrofitService: MealInterface? = null

        fun getInstance() : MealInterface {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://www.themealdb.com/api/json/v1/1/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(MealInterface::class.java)
            }
            return retrofitService!!
        }
    }
}