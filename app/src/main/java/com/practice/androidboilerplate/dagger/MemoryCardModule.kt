package com.practice.androidboilerplate.dagger

import android.util.Log
import dagger.Module
import dagger.Provides

@Module
class MemoryCardModule(val size: Int) {

    @Provides
    fun providesMemoryCard(): MemoryCard {
        Log.d("MYTAG", size.toString())
        return MemoryCard()
    }
}