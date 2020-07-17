package com.example.appcentflickr.view_models

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelFactory = module {
    viewModel {
        UploadListViewModel(get())
    }
}