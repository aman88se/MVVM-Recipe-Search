package com.semsofs.foodarchitectmvvm.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.semsofs.foodarchitectmvvm.model.Meal

@Database(entities = [Meal::class], version = 1)
@TypeConverters(RecipeTypeConverter::class)
abstract class RecipeDatabase: RoomDatabase() {
    abstract fun recipeDao(): RecipeDao

    companion object{
        @Volatile
        var INSTANCE: RecipeDatabase? = null

        @Synchronized
        fun getInstance(context: Context): RecipeDatabase{
            if (INSTANCE == null){
                INSTANCE = Room.databaseBuilder(
                    context,RecipeDatabase::class.java,"meal.db"
                ).fallbackToDestructiveMigration().build()
            }
            return INSTANCE as RecipeDatabase
        }


    }
}