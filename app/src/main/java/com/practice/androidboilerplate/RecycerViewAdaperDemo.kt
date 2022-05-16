package com.practice.androidboilerplate

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class RecycerViewAdaperDemo(private var item: List<Fruit>, private var onclick: (Fruit) -> Unit) :
    RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var layoutInflater = LayoutInflater.from(parent.context)
        var item = layoutInflater.inflate(R.layout.item, parent, false)
        return MyViewHolder(item)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(item[position],onclick)
    }

    override fun getItemCount(): Int {
        return item.size
    }
}

class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    fun bind(fruit: Fruit, onclick: (Fruit) -> Unit) {
        var tvName = view.findViewById<TextView>(R.id.tvname)
        tvName.text = fruit.name
        tvName.setOnClickListener {
           // Toast.makeText(view.context, fruit.name, Toast.LENGTH_SHORT).show()
            onclick(fruit)
        }
    }

}