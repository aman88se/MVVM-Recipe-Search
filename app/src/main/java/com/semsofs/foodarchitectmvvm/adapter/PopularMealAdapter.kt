package com.semsofs.foodarchitectmvvm.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.semsofs.foodarchitectmvvm.databinding.PopularItemListViewBinding
import com.semsofs.foodarchitectmvvm.model.PopularMealList
import com.semsofs.foodarchitectmvvm.model.PopularMeals

class PopularMealAdapter: RecyclerView.Adapter<PopularMealAdapter.PopularViewHolder>() {

    var popularMealList = ArrayList<PopularMeals>()

    fun setPopularMealList(popularMealList:  List<PopularMeals> ){

        this.popularMealList = popularMealList as ArrayList<PopularMeals>

        notifyDataSetChanged()

    }


    inner class PopularViewHolder(val binding : PopularItemListViewBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
        return PopularViewHolder(
            PopularItemListViewBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {

        Glide.with(holder.itemView).load(popularMealList[position].strMealThumb).into(holder.binding.popularItemImage)
//        holder.binding.popularMealName.text = popularMealList[position].strMeal
    }

    override fun getItemCount(): Int {

        return popularMealList.size
    }

}