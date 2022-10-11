package com.semsofs.foodarchitectmvvm.Fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.semsofs.foodarchitectmvvm.R
import com.semsofs.foodarchitectmvvm.RandomMealDetailActivity
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

    private lateinit var randomMeal: Meal

    companion object{

//        const val MEALCATEGORY = "com.semsofs.foodarchitectmvvm.Fragments.categoryMeal"
        const val MEALNAME = "com.semsofs.foodarchitectmvvm.Fragments.nameMeal"
        const val MEALTHUMB = "com.semsofs.foodarchitectmvvm.Fragments.thumbMeal"


    }

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

        binding.randomMealCard.setOnClickListener {
            val intent = Intent(this@HomeFragment.requireContext(),RandomMealDetailActivity::class.java)

//            v
            intent.putExtra(MEALNAME,randomMeal.strMeal)
            intent.putExtra(MEALTHUMB,randomMeal.strMealThumb)

            startActivity(intent)

        }

        viewModel.getAllMeals()
        viewModel.mealList.observe(viewLifecycleOwner,{ meal ->

//            Log.d("HomeFragment","${it.idMeal}")
            Glide.with(this).load(meal!!.strMealThumb).into(binding.randomMealImage)

            this.randomMeal = meal

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
            layoutManager = GridLayoutManager(context,3,GridLayoutManager.HORIZONTAL,false)
            adapter = categoryListAdapter
        }

    }

}