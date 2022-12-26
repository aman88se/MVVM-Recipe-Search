package com.semsofs.foodarchitectmvvm.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.semsofs.foodarchitectmvvm.model.Meal

@Dao
interface RecipeDao {

    //If recipe already exist update it      ↓
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(recipe: Meal)

    @Delete
    suspend fun delete(recipe: Meal)

    @Query("SELECT * FROM recipeDetails")
    fun getAllRecipe():LiveData<List<Meal>>
}