package com.kcv.moviesearch.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.kcv.moviesearch.R
import com.kcv.moviesearch.databinding.FragmentSearchBinding

class SearchFragment : Fragment(){

    private val viewModel: SearchViewModel by lazy {
        ViewModelProvider(this).get(SearchViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
    val binding = FragmentSearchBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel


        binding.recyclerSearchResults.adapter = SearchAdapter(SearchAdapter.OnClickListener {
            viewModel.displaySearchDetails(it)
        })


        viewModel.showSearchDetail.observe(this, Observer {
            if ( null != it ) {
                this.findNavController().navigate(
                    SearchFragmentDirections.actionShowDetail(it))
                viewModel.displaySearchDetailsComplete()
            }
        })


        return binding.root
    }




}