package com.semsofs.foodarchitectmvvm.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.semsofs.foodarchitectmvvm.adapter.CategoryListAdapter
import com.semsofs.foodarchitectmvvm.api.MealInstance
import com.semsofs.foodarchitectmvvm.api.MealInterface
import com.semsofs.foodarchitectmvvm.databinding.FragmentHomeBinding
import com.semsofs.foodarchitectmvvm.model.Meal
import com.semsofs.foodarchitectmvvm.model.RandomMealList
import com.semsofs.foodarchitectmvvm.repository.MealRepository
import com.semsofs.foodarchitectmvvm.viewModel.HomeFragViewModel
import com.semsofs.foodarchitectmvvm.viewModel.HomeFragViewModelProvider
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val TAG = "HomeFragment"
    lateinit var viewModel: HomeFragViewModel
    private val retrofitService = MealInterface.getInstance()

    lateinit var categoryListAdapter: CategoryListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this,HomeFragViewModelProvider(MealRepository(retrofitService)))
            .get(HomeFragViewModel::class.java)




    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        viewModel.getAllMeals()
        viewModel.mealList.observe(viewLifecycleOwner,{

            Log.d("HomeFragment","${it.idMeal}")
            Glide.with(this).load(it.strMealThumb).into(binding.randomMealImage)

        })


        CategoryRecyclerView()

        viewModel.getAllCategory()
        viewModel.mealCategoryList.observe(viewLifecycleOwner,{ categories ->

                categoryListAdapter.setCategoryList(categories)

        })
    }

    private fun CategoryRecyclerView() {

        categoryListAdapter = CategoryListAdapter()
        binding.categoryRecyclerView.apply {
            layoutManager = GridLayoutManager(context,1,GridLayoutManager.HORIZONTAL,false)
            adapter = categoryListAdapter
        }

    }

}