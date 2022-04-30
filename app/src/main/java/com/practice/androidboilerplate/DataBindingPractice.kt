package com.practice.androidboilerplate

import android.opengl.Visibility
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.practice.androidboilerplate.databinding.DatabindingExampleBinding
import com.practice.androidboilerplate.databinding.Databindingexampletest1Binding
import com.practice.androidboilerplate.databinding.Databindingexampletest2Binding

class DataBindingPractice : AppCompatActivity() {
    private lateinit var bindingExample: Databindingexampletest2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /* bindingExample = DataBindingUtil.setContentView(this, R.layout.databinding_example)
         bindingExample.button.setOnClickListener {
             bindingExample.textView.text=bindingExample.name.text
         }

          //applying scope function

         bindingExample.apply {
             button.setOnClickListener {
                 textView.text = name.text
             }
         }*/

/*        bindingExample = DataBindingUtil.setContentView(this, R.layout.databindingexampletest1)
        bindingExample.button2.setOnClickListener { display() }*/
        bindingExample = DataBindingUtil.setContentView(this, R.layout.databindingexampletest2)
        bindingExample.student = getStudent()
    }

    /*private fun display() {
        bindingExample.apply {
            if (progressBarTest.visibility == View.INVISIBLE) {
                progressBarTest.visibility = View.VISIBLE
                button2.text = "stop"
            } else {
                progressBarTest.visibility = View.INVISIBLE
                button2.text = "start"
            }

        }
}*/

    private fun getStudent(): Student {
        return Student(name = "test", email = "test@gmail.com")
    }


}






