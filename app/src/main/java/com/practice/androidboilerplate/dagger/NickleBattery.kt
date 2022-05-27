package com.practice.androidboilerplate.dagger

import android.util.Log
import javax.inject.Inject

class NickleBattery @Inject constructor() : Battery {
    override fun getPower() {
        Log.i("MYTAG", "Power with NicleBattery")
    }
}