package com.hyper.gallery.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.hyper.gallery.repository.PhotoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.http.Query
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: PhotoRepository) : ViewModel() {

    val list = repository.getPhotos().cachedIn(viewModelScope)

    private val currentQuery = MutableLiveData(DEFAULT_QUERY)

    val searchList = currentQuery.switchMap { queryString ->
        repository.searchPhotos(queryString).cachedIn(viewModelScope)
    }

    fun searchPhotos(query: String) {
        currentQuery.value = query
    }
    companion object {
        private const val DEFAULT_QUERY = "cats"
    }
}