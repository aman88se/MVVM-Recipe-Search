package com.semsofs.foodarchitectmvvm.Fragments


import android.app.FragmentTransaction
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.semsofs.foodarchitectmvvm.R
import com.semsofs.foodarchitectmvvm.databinding.FragmentRandomMealBinding


class RandomMealFragment : Fragment() {


    private lateinit var binding : FragmentRandomMealBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)






    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRandomMealBinding.inflate(inflater,container,false)
        return binding.root










    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.RandomDetailBackBtn.setOnClickListener {

            Navigation.findNavController(it).navigate(R.id.action_randomMealFragment_to_homeFragment)

        }

    }





}
