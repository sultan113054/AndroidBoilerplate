package com.practice.androidboilerplate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class ViewmodelPracticeViewModelFactory(private val total: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ViewModelPracticeViewModel::class.java))
            return ViewModelPracticeViewModel(total) as T
        throw IllegalArgumentException("Unknown view model class")
    }


}