package com.ozzy.kukaf1.service

import com.ozzy.kukaf1.models.responses.UploadsResponse
import io.reactivex.Observable
import retrofit2.http.*

interface ApiCallInterface {

    @GET(".")
    fun getUploadList(
        @Query("method") method : String,
        @Query("api_key") apiKey : String,
        @Query("format") format : String,
        @Query("nojsoncallback") noJsonCallback : Int,
        @Query("per_page") perPage : Int,
        @Query("page") page : Int

    ): Observable<UploadsResponse>

}