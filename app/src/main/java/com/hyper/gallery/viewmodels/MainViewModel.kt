package com.hyper.gallery.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hyper.gallery.models.Photo
import com.hyper.gallery.repository.PhotoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: PhotoRepository) : ViewModel() {

    val photoLiveData : LiveData<List<Photo>>
        get() = repository.photos

    init {
        viewModelScope.launch {
            repository.getPhotos()
        }
    }

}