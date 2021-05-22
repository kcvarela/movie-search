package com.kcv.moviesearch.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.kcv.moviesearch.databinding.FragmentSearchDetailBinding


class SearchDetailFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        @Suppress("UNUSED_VARIABLE")
        val application = requireNotNull(activity).application
        val binding = FragmentSearchDetailBinding.inflate(inflater)

        binding.setLifecycleOwner(this)
        val itemSearched = SearchDetailFragmentArgs.fromBundle(arguments!!).selectedItem
        val viewModelFactory = SearchDetailViewModelFactory(itemSearched, application)
        binding.viewModel = ViewModelProvider(
            this, viewModelFactory
        ).get(SearchDetailViewModel::class.java)

        return binding.root
    }
}