package com.semsofs.foodarchitectmvvm.repository

import com.semsofs.foodarchitectmvvm.Database.RecipeDatabase
import com.semsofs.foodarchitectmvvm.Fragments.HomeFragment
import com.semsofs.foodarchitectmvvm.api.MealInterface

class  MealRepository(private val mealInterface: MealInterface) {

    fun getAllMeals() = mealInterface.getAllMeals()

    fun getAllCategory() = mealInterface.getAllCategories()

    fun getPopularMeals() = mealInterface.getPopularMeals("Breakfast")

    fun getCategoryList() = mealInterface.getCategoryList("Beef")



}