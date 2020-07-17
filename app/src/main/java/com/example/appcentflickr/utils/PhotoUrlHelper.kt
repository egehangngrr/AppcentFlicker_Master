package com.example.appcentflickr.utils

import com.example.appcentflickr.models.Upload
import com.ozzy.kukaf1.service.Urls

class PhotoUrlHelper {

    companion object{
        fun getImageUrl(upload : Upload) : String {
            return Urls.PHOTO_URL.replace("{farm-id}", upload.farm)
                .replace("{server-id}", upload.server)
                .replace("{id}", upload.id)
                .replace("{secret}", upload.secret)
        }
    }

}