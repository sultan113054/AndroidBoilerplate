package com.practice.androidboilerplate.worker

import android.content.Context
import android.util.Log
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.text.SimpleDateFormat
import java.util.*
import kotlin.contracts.Returns

class DownloadingWorker(context: Context, parameters: WorkerParameters) :
    Worker(context, parameters) {


    override fun doWork(): Result {

        try {
            for (i in 0 until 3000) {
                Log.i("Tag", "Downloading= $i")
            }

            return Result.success()
        } catch (e: Exception) {
            return Result.failure()
        }
    }
}