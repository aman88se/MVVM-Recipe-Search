package com.semsofs.foodarchitectmvvm.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.semsofs.foodarchitectmvvm.databinding.ActivityCategoryItemsBinding
import com.semsofs.foodarchitectmvvm.model.PopularMealList
import com.semsofs.foodarchitectmvvm.model.PopularMeals

class CatRecipeListAdapter: RecyclerView.Adapter<CatRecipeListAdapter.CatRecipeViewHolder>() {


    var catRecipeList = ArrayList<PopularMeals>()

    fun setAllCatRecipeList(catRecipeList: List<PopularMeals>){

        this.catRecipeList = catRecipeList as ArrayList<PopularMeals>

        notifyDataSetChanged()

    }

    inner class CatRecipeViewHolder(val binding: ActivityCategoryItemsBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatRecipeViewHolder {
        return CatRecipeViewHolder(
            ActivityCategoryItemsBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: CatRecipeViewHolder, position: Int) {
        Glide.with(holder.itemView).load(catRecipeList[position].strMealThumb).into(holder.binding.categoryItemImage)
        holder.binding.categoryItemName.text = catRecipeList[position].strMeal
    }

    override fun getItemCount(): Int {
        return catRecipeList.size
    }

}