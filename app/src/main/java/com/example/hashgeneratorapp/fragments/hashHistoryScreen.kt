package com.example.hashgeneratorapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.hashgeneratorapp.R
import com.example.hashgeneratorapp.databinding.FragmentNewHashScreenBinding
import com.example.hashgeneratorapp.viewmodel.HashViewModel

class hashHistoryScreen : Fragment() {

    private var _binding: FragmentNewHashScreenBinding? = null
    private val binding get() = _binding!!

    private lateinit var mHashViewModel: HashViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewHashScreenBinding.inflate(inflater, container, false)

        mHashViewModel = ViewModelProvider(this).get(HashViewModel::class.java)



        return binding.root
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}