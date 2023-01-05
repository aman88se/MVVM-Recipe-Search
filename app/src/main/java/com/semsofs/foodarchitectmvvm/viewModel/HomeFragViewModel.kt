package com.semsofs.foodarchitectmvvm.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semsofs.foodarchitectmvvm.Database.RecipeDatabase
import com.semsofs.foodarchitectmvvm.model.*
import com.semsofs.foodarchitectmvvm.repository.MealRepository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragViewModel(private val repository: MealRepository ): ViewModel() {

    val mealList = MutableLiveData<Meal>()
    val mealCategoryList = MutableLiveData<List<Category>>()
    val popularMealList = MutableLiveData<List<PopularMeals>>()



    fun getPopularItems(){

        val response = repository.getPopularMeals()
        response.enqueue(object : Callback<PopularMealList>{
            override fun onResponse(call: Call<PopularMealList>, response: Response<PopularMealList>){

                response.body()?.let { popularList->
                     popularMealList.value = response.body()!!.meals
                }
            }

            override fun onFailure(call: Call<PopularMealList>, t: Throwable) {
                Log.e("HomeFragViewModel",t.message.toString())
            }


        })

    }


    fun getAllCategory(){
        val response = repository.getAllCategory()
        response.enqueue(object : Callback<CategoryList>{
            override fun onResponse(call: Call<CategoryList>, response: Response<CategoryList>) {

                response.body()?.let { categoryList ->
                    mealCategoryList.value = response.body()!!.categories
                }

            }
            override fun onFailure(call: Call<CategoryList>, t : Throwable) {

                Log.e("HomeFragViewModel",t.message.toString())

            }

        })
    }

    fun getAllMeals(){

        val response = repository.getAllMeals()
        response.enqueue(object : Callback<RandomMealList>{
            override fun onResponse(call: Call<RandomMealList>, response: Response<RandomMealList>) {
                if(response.body() != null){
                    val randomMeal: Meal = response.body()!!.meals[0]
                    mealList.value = randomMeal
                }
            }
            override fun onFailure(call: Call<RandomMealList>, t: Throwable) {

                Log.i("HomeFragViewModel",t.message.toString())
            }


        })
    }





}