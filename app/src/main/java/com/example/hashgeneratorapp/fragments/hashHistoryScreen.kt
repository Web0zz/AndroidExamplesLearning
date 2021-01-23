package com.example.hashgeneratorapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hashgeneratorapp.R
import com.example.hashgeneratorapp.adapter.ListAdapter
import com.example.hashgeneratorapp.databinding.FragmentHashHistoryScreenBinding
import com.example.hashgeneratorapp.databinding.FragmentNewHashScreenBinding
import com.example.hashgeneratorapp.model.Hash
import com.example.hashgeneratorapp.viewmodel.HashViewModel
import kotlinx.coroutines.delay

class hashHistoryScreen : Fragment(), ListAdapter.ClickListener {

    private var _binding: FragmentHashHistoryScreenBinding? = null
    private val binding get() = _binding!!

    private lateinit var mHashViewModel: HashViewModel
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHashHistoryScreenBinding.inflate(inflater, container, false)

        val adapter = ListAdapter(this)
        recyclerView = binding.hashList
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        mHashViewModel = ViewModelProvider(this).get(HashViewModel::class.java)

        val hashObserver = Observer<List<Hash>> { hash ->
            adapter.setData(hash)
        }
        mHashViewModel.hashDatabaseList.observe(viewLifecycleOwner, hashObserver)

        binding.addButton.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.action_hashHistoryScreen_to_newHashScreen)
        }

        return binding.root
    }

    override fun ClickedItem(id: Int) {
        val action = hashHistoryScreenDirections.actionHashHistoryScreenToSelectedHashScreen(id)
        Navigation.findNavController(binding.root).navigate(action)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}