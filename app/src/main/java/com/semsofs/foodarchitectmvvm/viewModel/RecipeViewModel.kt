package com.semsofs.foodarchitectmvvm.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semsofs.foodarchitectmvvm.Database.RecipeDatabase
import com.semsofs.foodarchitectmvvm.api.MealInterface
import com.semsofs.foodarchitectmvvm.model.CategoryList
import com.semsofs.foodarchitectmvvm.model.Meal
import com.semsofs.foodarchitectmvvm.model.PopularMealList
import com.semsofs.foodarchitectmvvm.model.RandomMealList
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecipeViewModel(private val recipeDatabase: RecipeDatabase): ViewModel(){

    private var recipeDetailsLiveData = MutableLiveData<Meal>()

    fun getRecipeDetails(id: String){
        MealInterface.getInstance().getRecipeDetails(id).enqueue(object : Callback<RandomMealList>{
            override fun onResponse(
                call: Call<RandomMealList>,
                response: Response<RandomMealList>
            ) {
                if (response.body()!=null){
                    recipeDetailsLiveData.value = response.body()!!.meals[0]
                }
                else
                    return
            }

            override fun onFailure(call: Call<RandomMealList>, t: Throwable) {
                Log.d("RandomMealDetailActivity",t.message.toString())
            }

        })

    }

    fun observeRecipeDetailLiveData(): LiveData<Meal>{

        return recipeDetailsLiveData
    }

    fun insertRecipe(meal: Meal){
        viewModelScope.launch {
            recipeDatabase.recipeDao().upsert(meal)
        }
    }

    fun deleteMeal(meal: Meal){
        viewModelScope.launch {
            recipeDatabase.recipeDao().delete(meal)
        }
    }
}