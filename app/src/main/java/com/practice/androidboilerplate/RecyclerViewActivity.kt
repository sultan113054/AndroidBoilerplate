package com.practice.androidboilerplate

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewActivity : AppCompatActivity() {
    var list =
        listOf<Fruit>(Fruit("Apple"), Fruit("Banana"), Fruit("jackfruit"), Fruit("Watermelon"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recyclerview_layout)
        var recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.setBackgroundColor(Color.YELLOW)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = RecycerViewAdaperDemo(list) { item: Fruit ->
            clicked(item)
        }

    }

    private fun clicked(fruit: Fruit) {
        Toast.makeText(this@RecyclerViewActivity, fruit.name, Toast.LENGTH_SHORT).show()

    }
}