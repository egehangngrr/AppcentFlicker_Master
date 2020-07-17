package com.ozzy.kukaf1.models.responses

import com.example.appcentflickr.models.Upload

class UploadsResponse {

    lateinit var photos : Photos


    class Photos{
        var total : Int? = null
        var photo: List<Upload>? = null
    }
}