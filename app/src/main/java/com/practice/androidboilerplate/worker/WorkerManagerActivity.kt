package com.practice.androidboilerplate.worker

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.work.*
import com.practice.androidboilerplate.R
import java.util.concurrent.TimeUnit

class WorkerManagerActivity : AppCompatActivity() {
    companion object {
        const val KEY_VALUE = "key_value"
    }

    private lateinit var textView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.worker_demo_1)
        var button: Button = findViewById(R.id.btn_start)
        textView = findViewById(R.id.tv_value)
        button.setOnClickListener {
            // oneTimeRequest()
            periodicWorkRequest()
        }

    }

    private fun oneTimeRequest() {
        var workManager = WorkManager.getInstance(applicationContext)
        var data: Data = Data.Builder().putInt(KEY_VALUE, 1200).build()
        val constraint = Constraints.Builder().setRequiresCharging(true)
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        val uploadRequest =
            OneTimeWorkRequest.Builder(UploadWorker::class.java)
                .setInputData(data)
                .setConstraints(constraint).build()
        val filterIngWorker =
            OneTimeWorkRequest.Builder(FilterIngWorker::class.java)
                .setInputData(data)
                .setConstraints(constraint).build()
        val compressingWorker =
            OneTimeWorkRequest.Builder(CompressingWorker::class.java)
                .setInputData(data)
                .setConstraints(constraint).build()

        val downloadingWorker =
            OneTimeWorkRequest.Builder(DownloadingWorker::class.java)
                .setInputData(data)
                .setConstraints(constraint).build()

        // for one time request
        // workManager.enqueue(uploadRequest)

        // for chaining request
//        workManager.beginWith(filterIngWorker)
//            .then(compressingWorker)
//            .then(uploadRequest)
//            .enqueue()

        // for parallel request

        val parallelWorks = mutableListOf<OneTimeWorkRequest>()
        parallelWorks.add(downloadingWorker)
        parallelWorks.add(filterIngWorker)

        workManager.beginWith(parallelWorks)
            .then(compressingWorker)
            .then(uploadRequest)
            .enqueue()

        workManager.getWorkInfoByIdLiveData(uploadRequest.id).observe(this) {
            textView.text = it.state.name
            if (it.state.isFinished) {
                var data = it.outputData
                Toast.makeText(this, data.getString(UploadWorker.KEY_WORKER), Toast.LENGTH_LONG)
                    .show()
            }
        }

    }

    private fun periodicWorkRequest() {
        var periodicWorkRequest =
            PeriodicWorkRequest.Builder(UploadWorker::class.java, 16, TimeUnit.MINUTES)
                .build()
        WorkManager.getInstance(applicationContext).enqueue(periodicWorkRequest)

    }
}