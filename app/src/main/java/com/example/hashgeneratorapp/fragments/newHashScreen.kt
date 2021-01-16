package com.example.hashgeneratorapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewHashScreenBinding.inflate(inflater, container, false)

        mHashViewModel = ViewModelProvider(this).get(HashViewModel::class.java)

        //Arrayadapter for spinner
        ArrayAdapter.createFromResource(
                requireContext(),
                R.array.hash_algorithms,
                R.layout.support_simple_spinner_dropdown_item
        ).also { adapter ->
            adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
            binding.hashSelectedInput.adapter = adapter
        }
        //To get selected item
        lateinit var hashSelected: String
        binding.hashSelectedInput.onItemSelectedListener = object : AdapterView.OnItemSelectedListener  {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                hashSelected = parent?.getItemAtPosition(position).toString()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }


        binding.generateButton.setOnClickListener {
            val tag_text = binding.hashTagInput.text.toString()
            val will_hash = binding.typedText.text.toString()

            if(!checkisempty(tag_text,will_hash)){
                val hashed = mHashViewModel.getHashText(will_hash,hashSelected)
                val new_data = Hash(0,tag_text,hashSelected,hashed)

                mHashViewModel.addNewHash(new_data)
                Toast.makeText(context,"Hash Created",Toast.LENGTH_SHORT).show()

                Navigation.findNavController(binding.root).navigate(R.id.action_newHashScreen_to_hashHistoryScreen)
            } else {
                Toast.makeText(context,"Something is missing",Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }

    private fun checkisempty(tag: String, text: String): Boolean{
        if(tag == ""  || text == "") {
            return true
        }
        return false
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}