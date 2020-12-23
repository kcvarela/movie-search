package com.kcv.moviesearch.presentation

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kcv.moviesearch.domain.Search

class SearchDetailViewModelFactory (
    private val itemSearched: Search,
    private val application: Application): ViewModelProvider.Factory{
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchDetailViewModel::class.java)) {
            return SearchDetailViewModel(itemSearched, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
