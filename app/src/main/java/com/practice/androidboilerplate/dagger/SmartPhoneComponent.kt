package com.practice.androidboilerplate.dagger

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [MemoryCardModule::class, NCBatterModule::class])
interface SmartPhoneComponent {
   // fun getSmartPhone(): SmartPhone

    // field injection
    fun inject(mainActivity: DaggerMainActivity)
}