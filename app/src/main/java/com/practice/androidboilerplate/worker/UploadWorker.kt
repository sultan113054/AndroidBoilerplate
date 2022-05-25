package com.practice.androidboilerplate.worker

import android.content.Context
import android.util.Log
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.text.SimpleDateFormat
import java.util.*
import kotlin.contracts.Returns

class UploadWorker(context: Context, parameters: WorkerParameters) : Worker(context, parameters) {

    companion object {
        const val KEY_WORKER = "key_worker"
    }

    override fun doWork(): Result {

        var int = inputData.getInt(WorkerManagerActivity.KEY_VALUE, 0)
        try {
            for (i in 0 until 1000) {
                Log.i("Tag", "value=$i")
            }
            var time = SimpleDateFormat("dd/M/yy hh:mm:ss")
            var date = time.format(Date())
            var data = Data.Builder().putString(KEY_WORKER, date).build()
            return Result.success(data)
        } catch (e: Exception) {
            return Result.failure()
        }
    }
}