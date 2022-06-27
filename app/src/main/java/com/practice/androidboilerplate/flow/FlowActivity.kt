package com.practice.androidboilerplate.flow

import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import android.util.Log.d
import android.util.Log.i
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.practice.androidboilerplate.R
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import java.util.concurrent.TimeUnit

class FlowActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_test)

        // producer part
        var flow = flow<Int> {
            for (i in 1..10) {
                emit(i)
                Log.i("mytag", " producer $i")

                delay(500)
            }
        }

        // consumer part

        //    CoroutineScope(Dispatchers.Main).launch {
//            flow.buffer().collect {
//                delay(2000)
//                Log.i("mytag", "consumer $it")
//            }
//            flow.collectLatest{
//                delay(2000)
//                Log.i("mytag", "consumer $it")
//            }

        //  }

        var text = findViewById<TextView>(R.id.circumference_name_text_view)
        var i = 0
      var item=  CoroutineScope(Dispatchers.Main)
            .launchPeriodicAsync(TimeUnit.SECONDS.toMillis(10)) {

                text.text = (i++).toString()
            }
        item.start()

    }

    private fun CoroutineScope.launchPeriodicAsync(repeatMillis: Long, action: () -> Unit) =
        this.async {
            while (isActive) {
                action()
                delay(repeatMillis)
            }
        }
}