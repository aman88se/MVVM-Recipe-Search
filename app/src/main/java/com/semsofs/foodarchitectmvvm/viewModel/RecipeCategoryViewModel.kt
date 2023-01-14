package com.semsofs.foodarchitectmvvm.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.semsofs.foodarchitectmvvm.api.MealInterface
import com.semsofs.foodarchitectmvvm.model.PopularMealList
import com.semsofs.foodarchitectmvvm.model.PopularMeals
import com.semsofs.foodarchitectmvvm.repository.MealRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecipeCategoryViewModel : ViewModel() {

    private var recipeCategoryLiveData = MutableLiveData<List<PopularMeals>>()

    fun getCategoryList(categoryName: String ){

        MealInterface.getInstance().getCategoryList(categoryName).enqueue(object : Callback<PopularMealList>{
            override fun onResponse(
                call: Call<PopularMealList>,
                response: Response<PopularMealList>
            ) {
                response.body()?.let { categoryList ->
                    recipeCategoryLiveData.postValue(categoryList.meals)
                }
            }

            override fun onFailure(call: Call<PopularMealList>, t: Throwable) {
                Log.e("RecipeCategoryViewModel",t.message.toString())
            }

        })

    }

    fun observeCategoryLiveData(): LiveData<List<PopularMeals>>{

        return recipeCategoryLiveData

    }


}