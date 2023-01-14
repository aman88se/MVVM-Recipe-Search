//package com.semsofs.foodarchitectmvvm.viewModel
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.ViewModelProvider
//import com.semsofs.foodarchitectmvvm.repository.MealRepository
//
//class RecipeCategoryViewModelProvider(private val repository: MealRepository): ViewModelProvider.Factory {
//
//    override fun <T: ViewModel> create(modelClass: Class<T>): T {
//
//        return if (modelClass.isAssignableFrom(RecipeCategoryViewModel::class.java)){
//            RecipeCategoryViewModel(this.repository) as T
//        }else{
//
//            throw IllegalAccessException("ViewModel Not Found")
//        }
//
//    }
//
//}