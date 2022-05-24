package com.practice.androidboilerplate.room

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.practice.androidboilerplate.R
import com.practice.androidboilerplate.databinding.RoomItemBinding

class SubscriberRecyclerview(private val clicked:(Subscriber)->Unit) :
    RecyclerView.Adapter<Myviewholder>() {
    private var subscribers= ArrayList<Subscriber>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Myviewholder {
        var layoutInflater = LayoutInflater.from(parent.context)
        var binding: RoomItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.room_item,parent, false)
        return Myviewholder(binding)
    }

    override fun onBindViewHolder(holder: Myviewholder, position: Int) {
        holder.bind(subscribers[position],clicked)
    }

    override fun getItemCount(): Int {
        return subscribers.size
    }
    fun setList(subscriber: List<Subscriber>){
        subscribers.clear()
        subscribers.addAll(subscriber)
    }
}

class Myviewholder(private val bind: RoomItemBinding) : RecyclerView.ViewHolder(bind.root) {
    fun bind(subscriber: Subscriber,clicked:(Subscriber)->Unit) {
        bind.tvName.text = subscriber.name
        bind.tvEmail.text = subscriber.email
        bind.layout.setOnClickListener{
            clicked(subscriber)
        }
    }
}