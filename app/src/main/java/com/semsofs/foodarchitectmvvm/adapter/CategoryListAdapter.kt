package com.semsofs.foodarchitectmvvm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.semsofs.foodarchitectmvvm.R
import com.semsofs.foodarchitectmvvm.model.Category

class CategoryListAdapter : RecyclerView.Adapter<CategoryListAdapter.MealViewHolder>() {


    inner class MealViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val categoryImage : ImageView

        init {

            categoryImage = itemView.findViewById(R.id.categoryImage)

        }

    }

    var meals = mutableListOf<Category>()

    fun setMealList(meals : List<Category>){

        this.meals = meals.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.category_list_item_view,parent,false)
        return MealViewHolder(view)
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        val meal = meals[position]

        Glide.with(holder.itemView.context).load(meal.strCategoryThumb).into(holder.categoryImage)
    }

    override fun getItemCount(): Int {
        return meals.size
    }
}