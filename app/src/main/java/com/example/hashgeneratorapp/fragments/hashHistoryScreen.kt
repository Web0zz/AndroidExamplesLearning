package com.example.hashgeneratorapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hashgeneratorapp.R
import com.example.hashgeneratorapp.adapter.ListAdapter
import com.example.hashgeneratorapp.databinding.FragmentHashHistoryScreenBinding
import com.example.hashgeneratorapp.databinding.FragmentNewHashScreenBinding
import com.example.hashgeneratorapp.viewmodel.HashViewModel

class hashHistoryScreen : Fragment() {

    private lateinit var mHashViewModel: HashViewModel
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_hash_history_screen, container, false)

        val adapter = ListAdapter()
        recyclerView = view.findViewById(R.id.hash_list)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        mHashViewModel = ViewModelProvider(this).get(HashViewModel::class.java)
        mHashViewModel.hashDatabaseList.observe(viewLifecycleOwner, Observer { hash ->
            adapter.setData(hash)
        })

        return view
    }
}