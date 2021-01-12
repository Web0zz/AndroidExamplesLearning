package com.example.hashgeneratorapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.hashgeneratorapp.R
import com.example.hashgeneratorapp.databinding.FragmentHomeScreenBinding
import com.example.hashgeneratorapp.databinding.FragmentNewHashScreenBinding
import com.example.hashgeneratorapp.model.Hash
import com.example.hashgeneratorapp.viewmodel.HashViewModel


class newHashScreen : Fragment() {

    private var _binding: FragmentNewHashScreenBinding? = null
    private val binding get() = _binding!!

    private lateinit var mHashViewModel: HashViewModel

    override fun onResume() {
        super.onResume()

        val hashAlgorithms = resources.getStringArray(R.array.hash_algorithms)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.drop_down_items, hashAlgorithms)
        binding.hashSelected.setAdapter(arrayAdapter)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewHashScreenBinding.inflate(inflater, container, false)

        mHashViewModel = ViewModelProvider(this).get(HashViewModel::class.java)

        binding.generateButton.setOnClickListener {
            val tag_text = binding.hashTag.text.toString()
            val hash_style = binding.hashSelected.text.toString()
            val will_hash = binding.typedText.text.toString()

            if(!checkisempty(tag_text,hash_style,will_hash)){
                val hashed = mHashViewModel.getHashText(will_hash,hash_style)
                val new_data = Hash(0,tag_text,hash_style,hashed)

                mHashViewModel.addNewHash(new_data)
                Toast.makeText(context,"Hash Created",Toast.LENGTH_SHORT).show()

                Navigation.findNavController(binding.root).navigate(R.id.action_newHashScreen_to_hashHistoryScreen)
            } else {
                Toast.makeText(context,"Something is missing",Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }

    fun checkisempty(tag: String, style: String, text: String): Boolean{
        if(tag == "" || style == "" || text == "") {
            return true
        }
        return false
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}