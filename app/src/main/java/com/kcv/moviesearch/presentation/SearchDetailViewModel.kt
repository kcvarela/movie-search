package com.kcv.moviesearch.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kcv.moviesearch.domain.Search

class SearchDetailViewModel(
    @Suppress("UNUSED_PARAMETER") itemSearched: Search,
    app: Application
) :
    AndroidViewModel(app) {

    private val _selectedItemSearched = MutableLiveData<Search>()
    val selectedItemSearched: LiveData<Search>
        get() = _selectedItemSearched

    init {
        _selectedItemSearched.value = itemSearched
    }
}
