package com.practice.androidboilerplate.dagger

import android.app.Application

class DaggerApplication: Application() {
    lateinit var smartPhoneComponent:SmartPhoneComponent
    override fun onCreate() {
        smartPhoneComponent=initDagger()
        super.onCreate()
    }

    fun initDagger():SmartPhoneComponent=DaggerSmartPhoneComponent.builder().memoryCardModule(MemoryCardModule(200)).build();
}