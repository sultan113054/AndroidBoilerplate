package com.practice.androidboilerplate

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewModelPracticeViewModel(total: Int) : ViewModel() {
    // var count=0
    //  var count=MutableLiveData<Int>()

    private var count = MutableLiveData<Int>()
    var value=MutableLiveData<String>()
    var input=MutableLiveData<String>()

    val countvalue: LiveData<Int>
        get() = count

    init {
        //   count=total
        count.value = total
    }

    //     fun getCurrentcount():Int{
//         return count
//     }
//
//    fun getUpdatedcount(value:Int):Int{
//        count+=value;
//        return count;
//    }
//    fun setUpdate(value: Int) {
//        count.value = count.value?.plus(value)
//    }
    fun setUpdate() {
        var input: Int =input.value!!.toInt()
        count.value = count.value?.plus(input)
    }

}