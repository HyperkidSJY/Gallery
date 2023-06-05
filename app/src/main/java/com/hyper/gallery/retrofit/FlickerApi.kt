package com.hyper.gallery.retrofit

import com.hyper.gallery.models.GalleryResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface FlickerApi {

    @GET("services/rest")
    suspend fun getPhotos(
        @Query("method") method : String,
        @Query("per_page") per_page : Int,
        @Query("page") page : Int,
        @Query("api_key") api_key : String,
        @Query("format") format : String,
        @Query("nojsoncallback") no_json_callback : Boolean,
        @Query("extras") extras: String
    ) : Response<GalleryResponse>

    @GET("services/rest")
    suspend fun searchPhotos(
        @Query("method") method : String,
        @Query("api_key") api_key : String,
        @Query("format") format : String,
        @Query("nojsoncallback") no_json_callback : Boolean,
        @Query("extras") extras: String,
        @Query("text") text: String
    ) : Response<GalleryResponse>


}