package com.practice.androidboilerplate.dagger

import android.util.Log
import dagger.Module
import javax.inject.Inject

//class MemoryCard @Inject constructor() {

class MemoryCard {

    init {
        Log.i("MYTAG", "Memory Card Constructed")
    }

    fun getSpaceAvailablity() {
        Log.i("MYTAG", "Memory space available")
    }
}