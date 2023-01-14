package com.semsofs.foodarchitectmvvm.Activitiy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.semsofs.foodarchitectmvvm.Fragments.HomeFragment
import com.semsofs.foodarchitectmvvm.adapter.CatRecipeListAdapter
import com.semsofs.foodarchitectmvvm.databinding.ActivityCategoryItemsBinding
import com.semsofs.foodarchitectmvvm.databinding.CategoryItemRvBinding
import com.semsofs.foodarchitectmvvm.viewModel.RecipeCategoryViewModel

class CategoryItemsActivity : AppCompatActivity() {

    lateinit var binding : CategoryItemRvBinding
    lateinit var recipeCategoryViewModel: RecipeCategoryViewModel
    lateinit var catRecipeListAdapter: CatRecipeListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CategoryItemRvBinding.inflate(layoutInflater)
        setContentView(binding.root)


        recipeCategoryViewModel = ViewModelProvider(this).get(RecipeCategoryViewModel::class.java)

        recipeCategoryViewModel.getCategoryList(intent.getStringExtra(HomeFragment.CATEGORYLIST)!!)

        recipeCategoryViewModel.observeCategoryLiveData().observe(this, { recipeList ->

            catRecipeListAdapter.setAllCatRecipeList(recipeList)

        })

        setCatListRecyclerView()



    }

    private fun setCatListRecyclerView() {
        catRecipeListAdapter = CatRecipeListAdapter()
        binding.categoryItemRv.apply {
            layoutManager = GridLayoutManager(context, 1, GridLayoutManager.VERTICAL, false)
            adapter = catRecipeListAdapter
        }

    }
}