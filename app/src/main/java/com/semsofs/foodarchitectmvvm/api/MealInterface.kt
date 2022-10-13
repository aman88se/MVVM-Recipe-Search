package com.semsofs.foodarchitectmvvm.api

import com.semsofs.foodarchitectmvvm.model.CategoryList
import com.semsofs.foodarchitectmvvm.model.Meal
import com.semsofs.foodarchitectmvvm.model.PopularMealList
import com.semsofs.foodarchitectmvvm.model.RandomMealList
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

    @GET("filter.php?")
    fun getPopularMeals(@Query("c") Category: String): Call<PopularMealList>

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