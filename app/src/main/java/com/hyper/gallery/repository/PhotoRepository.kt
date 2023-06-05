package com.hyper.gallery.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.hyper.gallery.paging.PhotoPagingSource
import com.hyper.gallery.paging.SearchPagingSource
import com.hyper.gallery.retrofit.FlickerApi
import javax.inject.Inject

class PhotoRepository@Inject constructor(private val flickerApi: FlickerApi) {

    fun getPhotos() = Pager(
        config = PagingConfig(pageSize = 20, maxSize = 100),
        pagingSourceFactory = {PhotoPagingSource(flickerApi)}
    ).liveData

    fun searchPhotos(query: String) = Pager(
        config = PagingConfig(pageSize = 100, maxSize = 400),
        pagingSourceFactory = {SearchPagingSource(flickerApi,query)}
    ).liveData

}