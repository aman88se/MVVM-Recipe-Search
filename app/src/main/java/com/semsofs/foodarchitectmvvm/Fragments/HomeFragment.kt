package com.semsofs.foodarchitectmvvm.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.semsofs.foodarchitectmvvm.Activitiy.CategoryItemsActivity
import com.semsofs.foodarchitectmvvm.Activitiy.RandomMealDetailActivity
import com.semsofs.foodarchitectmvvm.adapter.CategoryListAdapter
import com.semsofs.foodarchitectmvvm.adapter.PopularMealAdapter
import com.semsofs.foodarchitectmvvm.api.MealInterface
import com.semsofs.foodarchitectmvvm.databinding.FragmentHomeBinding
import com.semsofs.foodarchitectmvvm.model.Meal
import com.semsofs.foodarchitectmvvm.repository.MealRepository
import com.semsofs.foodarchitectmvvm.viewModel.HomeFragViewModel
import com.semsofs.foodarchitectmvvm.viewModel.HomeFragViewModelProvider

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val TAG = "HomeFragment"
    lateinit var viewModel: HomeFragViewModel
    private val retrofitService = MealInterface.getInstance()
    lateinit var categoryListAdapter: CategoryListAdapter
    lateinit var popularMealAdapter: PopularMealAdapter

    private lateinit var randomMeal: Meal

    companion object{

//        const val MEALVIDEO = "com.semsofs.foodarchitectmvvm.Fragments.mealVideo"
        const val INSTRUCTIONS = "com.semsofs.foodarchitectmvvm.Fragments.Instructions"
//        const val MEALNAME = "com.semsofs.foodarchitectmvvm.Fragments.nameMeal"
        const val MEALTHUMB = "com.semsofs.foodarchitectmvvm.Fragments.thumbMeal"
        const val MEALID = "com.semsofs.foodarchitectmvvm.Fragments.mealId"
        const val CATEGORYLIST = "com.semsofs.foodarchitectmvvm.Fragments.strCategory"


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
            val intent = Intent(this@HomeFragment.requireContext(), RandomMealDetailActivity::class.java)

//            v
//            intent.putExtra(MEALNAME,randomMeal.strMeal)
            intent.putExtra(MEALTHUMB,randomMeal.strMealThumb)
//            intent.putExtra(MEALVIDEO, randomMeal.strYoutube)
            intent.putExtra(INSTRUCTIONS, randomMeal.strInstructions)
            intent.putExtra(MEALID, randomMeal.idMeal)


            startActivity(intent)

        }

        viewModel.getAllMeals()
        viewModel.mealList.observe(viewLifecycleOwner) { meal ->

//            Log.d("HomeFragment","${it.idMeal}")
            Glide.with(this).load(meal!!.strMealThumb).into(binding.randomMealImage)

            this.randomMeal = meal

        }


        categoryRecyclerView()

        //Go from category to category List
        onCategoryClick()



        popularMealRecyclerView()


        //Set category list
        viewModel.getAllCategory()
        viewModel.mealCategoryList.observe(viewLifecycleOwner) { categories ->

            categoryListAdapter.setCategoryList(categories)

        }

        //Set popular meal list
        viewModel.getPopularItems()
        viewModel.popularMealList.observe(viewLifecycleOwner) { popularItems ->

            popularMealAdapter.setPopularMealList(popularItems)

        }
    }

    private fun onCategoryClick() {

        categoryListAdapter.onItemClick = {category ->
            val intent = Intent(activity, CategoryItemsActivity::class.java)
            intent.putExtra(CATEGORYLIST, category.strCategory)
            startActivity(intent)
        }

    }

    private fun categoryRecyclerView() {

        categoryListAdapter = CategoryListAdapter()
        binding.categoryRecyclerView.apply {
            layoutManager = GridLayoutManager(context,2,GridLayoutManager.HORIZONTAL,false)
            adapter = categoryListAdapter
        }

    }

    private fun popularMealRecyclerView(){

        popularMealAdapter = PopularMealAdapter()
        binding.popularItemRecyclerView.apply {
            layoutManager = GridLayoutManager(context,1,GridLayoutManager.HORIZONTAL,false)
            adapter = popularMealAdapter
        }

    }

}