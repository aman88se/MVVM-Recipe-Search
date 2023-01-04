package com.semsofs.foodarchitectmvvm.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.semsofs.foodarchitectmvvm.Database.RecipeDatabase

class RecipeViewModelProvider(private val recipeDatabase: RecipeDatabase): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return if (modelClass.isAssignableFrom(RecipeViewModel::class.java)){
            RecipeViewModel(this.recipeDatabase) as T
        }else{

            throw IllegalAccessException("ViewModel Not Found")
        }
    }
}