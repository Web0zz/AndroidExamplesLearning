package com.example.hashgeneratorapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.hashgeneratorapp.R
import com.example.hashgeneratorapp.databinding.FragmentHomeScreenBinding


class homeScreen : Fragment() {

    private var _binding: FragmentHomeScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeScreenBinding.inflate(inflater, container, false)

        binding.newHash.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.action_homeScreen_to_newHashScreen)
        }

        binding.hashHistory.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.action_homeScreen_to_hashHistoryScreen)
        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}