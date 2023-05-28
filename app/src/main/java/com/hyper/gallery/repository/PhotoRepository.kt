package com.hyper.gallery.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hyper.gallery.models.Photo
import com.hyper.gallery.retrofit.FlickerApi
import javax.inject.Inject

class PhotoRepository@Inject constructor(private val flickerApi: FlickerApi) {

    private val _photos = MutableLiveData<List<Photo>>()
    val photos : LiveData<List<Photo>>
        get() = _photos


    suspend fun getPhotos(){
        val result = flickerApi.getPhotos()
        if(result.isSuccessful && result.body() != null){
            _photos.postValue(result.body()!!.photos.photo)
        }
    }

}