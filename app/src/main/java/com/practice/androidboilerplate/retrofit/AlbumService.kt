package com.practice.androidboilerplate.retrofit

import retrofit2.Response
import retrofit2.http.*

interface AlbumService {
    @GET("albums")
    suspend fun getAlbums(): Response<Albums>
    @GET("albums")
    suspend fun getSortedAlbums(@Query("userId")id:Int): Response<Albums>

    @GET("albums/{id}")
    suspend fun getAlbumBuId(@Path(value = "id")id:Int):Response<AlbumsItem>

    @POST("albums")
    suspend fun uploadAlbum(@Body albumsItem: AlbumsItem):Response<AlbumsItem>
}