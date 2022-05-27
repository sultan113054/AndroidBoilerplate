package com.practice.androidboilerplate.dagger

import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class NCBatterModule {

//class NCBatterModule {
//    @Provides
//    fun providesNCBattey(nickleBattery: NickleBattery): Battery {
//        return nickleBattery
//    }

    @Binds
    abstract fun bindsNCBattey(nickleBattery: NickleBattery): Battery

}