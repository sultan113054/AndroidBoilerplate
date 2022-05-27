package com.practice.androidboilerplate.dagger

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.practice.androidboilerplate.R
import javax.inject.Inject

class DaggerMainActivity : AppCompatActivity() {
    @Inject
    lateinit var smartPhone: SmartPhone

    @Inject
    lateinit var memoryCard: MemoryCard
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


//        val smartPhone = SmartPhone(
//            Battery(),
//            SIMCard(ServiceProvider()),
//            MemoryCard()
//        )
//            .makeACallWithRecording()

        // DaggerSmartPhoneComponent.create().getSmartPhone().makeACallWithRecording()
        //  DaggerSmartPhoneComponent.create().inject(this)
//        DaggerSmartPhoneComponent.builder().memoryCardModule(MemoryCardModule(200)).build()
//            .inject(this)
//
//
//        smartPhone.makeACallWithRecording()
//        memoryCard.getSpaceAvailablity()
        (application as DaggerApplication).smartPhoneComponent.inject(this)
        smartPhone.makeACallWithRecording()
    }
}
