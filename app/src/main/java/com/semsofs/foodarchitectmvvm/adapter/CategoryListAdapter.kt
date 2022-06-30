package com.semsofs.foodarchitectmvvm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.semsofs.foodarchitectmvvm.R
import com.semsofs.foodarchitectmvvm.databinding.CategoryListItemViewBinding
import com.semsofs.foodarchitectmvvm.model.Category

class CategoryListAdapter : RecyclerView.Adapter<CategoryListAdapter.MealViewHolder>() {

    var categoryList = ArrayList<Category>()

    fun setCategoryList(categoryList : List<Category>){

        this.categoryList = categoryList as ArrayList<Category>
        notifyDataSetChanged()
    }


    inner class MealViewHolder(val binding : CategoryListItemViewBinding) : RecyclerView.ViewHolder(binding.root)





    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {

        return MealViewHolder(
            CategoryListItemViewBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {

        Glide.with(holder.itemView).load(categoryList[position].strCategoryThumb).into(holder.binding.categoryImage)
        holder.binding.categoryName.text = categoryList[position].strCategory


    }

    override fun getItemCount(): Int {
        return categoryList.size
    }
}