package com.semsofs.foodarchitectmvvm.api

import com.semsofs.foodarchitectmvvm.model.RandomMeal
import retrofit2.Response
import retrofit2.http.GET

interface MealInterface {

    @GET("random.php")
    suspend fun getMeal(): Response<RandomMeal>
}