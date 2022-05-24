package com.practice.androidboilerplate.room

import android.os.Bundle
import android.util.Log
import android.widget.Adapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.practice.androidboilerplate.R
import com.practice.androidboilerplate.databinding.RoomPracticeBinding
import java.time.Duration

class RoomMainActivity : AppCompatActivity() {
    private lateinit var binding: RoomPracticeBinding
    private lateinit var viewModel: SubscriberViewModel
    private lateinit var adapter: SubscriberRecyclerview
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.room_practice)

        val dao = SubscriberDatabase.getInstance(application)?.subscriberDao
        val repository = SubscriberRepository(dao!!)
        val factory = SubscriberViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(SubscriberViewModel::class.java)
        binding.subscriberViewModel = viewModel
        binding.lifecycleOwner = this
        initRecyclerView()
        viewModel.status.observe(this) {
            it.getContentIfNotHandled()?.let {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        }


    }

    fun initRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = SubscriberRecyclerview({ subscriber: Subscriber -> listItemClicked(subscriber) })
        binding.recyclerView.adapter = adapter
        displaySubscriberList()
    }

    fun displaySubscriberList() {
        viewModel.subscribers.observe(this) {
            adapter.setList(it)
            adapter.notifyDataSetChanged()
        }
    }

    fun listItemClicked(subscriber: Subscriber) {
        viewModel.initUpdateOrSave(subscriber)
    }
}