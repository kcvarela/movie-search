package com.kcv.moviesearch.presentation

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kcv.moviesearch.data.Api
import com.kcv.moviesearch.domain.Search
import com.kcv.moviesearch.domain.SearchResponse
import kotlinx.coroutines.launch


enum class OmdApiStatus { LOADING, ERROR, DONE }

class SearchViewModel : ViewModel() {

    private val _status = MutableLiveData<OmdApiStatus>()
    val status: LiveData<OmdApiStatus>
        get() = _status

    private val _searchResponse = MutableLiveData<SearchResponse>()
    val searchResponse: LiveData<SearchResponse>
        get() = _searchResponse

    private val _showSearchDetail = MutableLiveData<Search>()
    val showSearchDetail: LiveData<Search>
        get() = _showSearchDetail

    val isSeries = ObservableBoolean(false)

    val isMovie = ObservableBoolean(false)

    init {
        getSearchResults("")
    }

    private fun getSearchResults(editSearch: String) {
        viewModelScope.launch()
        {

            try {
                _searchResponse.value = Api.retrofitService.getMoviesByTitle(editSearch)
                _status.value = OmdApiStatus.DONE

            } catch (e: Exception) {
                Log.e("ViewModel", e.toString())
                _status.value = OmdApiStatus.ERROR
            }
        }
    }

    private fun getSeriesResults(editSearch: String) {
        viewModelScope.launch()
        {
            try {
                _searchResponse.value = Api.retrofitService.getSeriesByTitle(editSearch)
                _status.value = OmdApiStatus.DONE

            } catch (e: Exception) {
                Log.e("ViewModel", e.toString())
                _searchResponse.value = null
                _status.value = OmdApiStatus.ERROR
            }
        }
    }

    fun displaySearchDetails(searchDetail: Search) {
        _showSearchDetail.value = searchDetail
    }

    fun displaySearchDetailsComplete() {
        _showSearchDetail.value = null
    }

    fun onSearchClick(editSearchString: String) {
        if (isSeries.get()) {
            getSeriesResults(editSearchString)
        } else if (isMovie.get()) {
            getSearchResults(editSearchString)
        }
    }
}