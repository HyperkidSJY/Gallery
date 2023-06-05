package com.hyper.gallery.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.hyper.gallery.models.Photo
import com.hyper.gallery.retrofit.FlickerApi
import com.hyper.gallery.utils.Constants
import java.lang.Exception

class PhotoPagingSource(val flickerApi: FlickerApi) : PagingSource<Int,Photo>() {
    override fun getRefreshKey(state: PagingState<Int, Photo>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photo> {
        return try {
            val position = params.key ?: 1
            val response = flickerApi.getPhotos(
                Constants.METHOD, 20, position,
                Constants.API_KEY,
                Constants.FORMAT,
                Constants.NO_JSON_CALL_BACK,
                Constants.EXTRAS)
            LoadResult.Page(data = response.body()!!.photos.photo,
                prevKey = if (position == 1) null else position - 1,
                nextKey = if(position == response.body()!!.photos.pages) null else position + 1
            )
        }catch (e : Exception){
            LoadResult.Error(e)
        }
    }

}