package com.practice.androidboilerplate

import androidx.lifecycle.ViewModel

class ViewModelPracticeViewModel(total:Int) : ViewModel() {
    private var count=0
    init {
        count=total
    }
     fun getCurrentcount():Int{
         return count
     }

    fun getUpdatedcount(value:Int):Int{
        count+=value;
        return count;
    }
}