package com.example.appcentflickr.view_models

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appcentflickr.service.Repository
import com.ozzy.kukaf1.models.responses.UploadsResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class UploadListViewModel(private val repository: Repository) : ViewModel() {


    private val disposables = CompositeDisposable()
    private val uploadsData = MutableLiveData<UploadsResponse>()

    fun uploadsResponse(): MutableLiveData<UploadsResponse> {
        return uploadsData
    }


    fun getUploads(perPage : Int, page : Int){
        disposables.add(repository.executeGetUploadList(perPage, page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { d ->
            }
            .subscribe(
                { result ->
                    uploadsData.setValue(result)
                },
                {
                    Log.d("Error", it.localizedMessage)
                }
            ))
    }
}
