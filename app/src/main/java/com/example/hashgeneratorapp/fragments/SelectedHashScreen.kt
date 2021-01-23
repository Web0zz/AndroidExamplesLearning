package com.example.hashgeneratorapp.fragments

import android.content.ClipData
import android.content.ClipboardManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.hashgeneratorapp.R
import com.example.hashgeneratorapp.databinding.FragmentSelectedHashScreenBinding
import com.example.hashgeneratorapp.model.Hash
import com.example.hashgeneratorapp.viewmodel.HashViewModel
import kotlinx.coroutines.*

class selectedHashScreen : Fragment() {

    private var _binding: FragmentSelectedHashScreenBinding? = null
    private val binding get() = _binding!!

    val args: selectedHashScreenArgs by navArgs()

    private lateinit var mHashViewModel: HashViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSelectedHashScreenBinding.inflate(inflater, container, false)

        mHashViewModel = ViewModelProvider(this).get(HashViewModel::class.java)
        val myClipboard = getSystemService(requireContext(), ClipboardManager::class.java) as ClipboardManager

        mHashViewModel.getData(args.id)

        val hashObserver = Observer<Hash> { hash ->
            binding.currentHashText.text = hash.hash
            binding.currentHashTag.text = hash.hashTag
            binding.deleteButton.setOnClickListener {
                mHashViewModel.deleteHash(hash)
                Navigation.findNavController(binding.root).navigate(R.id.action_selectedHashScreen_to_hashHistoryScreen)
            }
            binding.copyButton.setOnClickListener {
                val clip: ClipData = ClipData.newPlainText("hash", hash.hash)
                myClipboard.setPrimaryClip(clip)
                Navigation.findNavController(binding.root).navigate(R.id.action_selectedHashScreen_to_hashHistoryScreen)
            }
        }

        mHashViewModel.currentHash.observe(viewLifecycleOwner, hashObserver)

        return binding.root
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}