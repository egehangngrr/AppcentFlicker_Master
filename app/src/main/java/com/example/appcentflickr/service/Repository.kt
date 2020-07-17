package com.example.appcentflickr.service

import com.ozzy.kukaf1.models.responses.UploadsResponse
import com.ozzy.kukaf1.service.ApiCallInterface
import com.ozzy.kukaf1.service.Urls
import io.reactivex.Observable

class Repository (private val apiCallInterface: ApiCallInterface){

    fun executeGetUploadList(perPage : Int, page : Int ): Observable<UploadsResponse>{
        return apiCallInterface.getUploadList(Urls.UPLOAD_METHOD,Urls.API_KEY, "json", 1, perPage, page)
    }
}