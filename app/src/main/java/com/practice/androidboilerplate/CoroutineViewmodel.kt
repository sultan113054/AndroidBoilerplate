package com.practice.androidboilerplate

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CoroutineViewmodel: ViewModel() {
    val count=MutableLiveData<Int>()

    init{
        count.value=0
    }
    fun updateCount(){
        count.value=(count.value)!!.plus(1)
    }

}