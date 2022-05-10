package com.practice.androidboilerplate

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.practice.androidboilerplate.databinding.CoroutinePracticeBinding
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

class CoroutinePractice : AppCompatActivity() {
    private var count = 0;
    private lateinit var viewmodel: CoroutineViewmodel
    private lateinit var binding: CoroutinePracticeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.coroutine_practice)
        viewmodel = ViewModelProvider(this).get(CoroutineViewmodel::class.java)
        binding.button4.setOnClickListener {
            //  binding.textView2.text = count++.toString()
        }
        binding.button5.setOnClickListener {
            //CoroutineScope(Dispatchers.Main).launch { download() }
            // CoroutineScope(Dispatchers.IO).launch { download() }
//             CoroutineScope(Dispatchers.IO).launch { download(10000) }
//            CoroutineScope(Dispatchers.IO).launch { downloadWitShow2() }
//            CoroutineScope(Dispatchers.IO).launch { download(10000) }
//            CoroutineScope(Dispatchers.IO).launch { downloadWitShow3() }

            CoroutineScope(Main).launch {
//                var a=getCount()
//                var b=getCount1()
//                var sum = a + b
////                Log.i("sum", sum.toString())
//                Log.i("sum", "start")
//                var a=async{getCount()}
//                var b=async{getCount1()}
//                var sum = a.await() + b.await()
//                Log.i("sum", sum.toString())
//                Log.i("sum", "start")
//                var a=async(IO){getCount()}
//                var b=async(IO){getCount1()}
//                var sum = a.await() + b.await()
//                Toast.makeText(applicationContext, "${sum}", Toast.LENGTH_SHORT).show()
                binding.textView2.text = getCounts1().toString()


            }

        }
    }

    private suspend fun getCounts1(): Int {
        count = 0
        lateinit var test: Deferred<Int>


        coroutineScope {
            launch(Dispatchers.IO) {
                delay(1000)
                count = 70
            }
            test = async(Dispatchers.IO) {
                delay(1000)
                return@async 70
            }


        }
        return count + test.await()
    }

    private suspend fun getCounts(): Int {
        count = 0
        CoroutineScope(Dispatchers.IO).launch {
            delay(1000)
            count = 70
        }
        val test = CoroutineScope(Dispatchers.IO).async {
            delay(1000)
            return@async 70
        }
        return count + test.await()
    }

    private suspend fun getCount(): Int {
        delay(10000)
        Log.i("sum", "cpunt 1")
        return 1000;
    }

    private suspend fun getCount1(): Int {
        delay(8000)
        Log.i("sum", "cpunt 2")
        return 1000;
    }

    private fun download(value: Int) {
        for (i in 1..200000) {
            Log.i("MYtag", "Downloading user $i in ${Thread.currentThread().name}")
        }
        //   Log.i("MYtag", "Downloading user data in ${Thread.currentThread().name}")

    }

    private suspend fun downloadWitShow3() {
        for (i in 1..2000) {
            withContext(Dispatchers.Main) {
                binding.textView3.text =
                    "Downloading user data $i in ${Thread.currentThread().name}"

            }
        }

    }

    private suspend fun downloadWitShow2() {
        for (i in 1..2000) {
            withContext(Dispatchers.Main) {
                binding.textView2.text =
                    "Downloading user data $i in ${Thread.currentThread().name}"

            }
        }

    }
}