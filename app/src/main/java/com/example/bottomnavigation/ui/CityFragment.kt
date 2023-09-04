package com.example.bottomnavigation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.bottomnavigation.databinding.FragmentCityBinding

class CityFragment:Fragment() {

    private lateinit var binding:FragmentCityBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCityBinding.inflate(inflater,container,false)
        return binding.root
    }


    companion object {
        val TAG: String = CityFragment::class.java.simpleName
        fun newInstance() = CityFragment()
    }
}