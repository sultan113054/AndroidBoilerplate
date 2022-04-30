package com.practice.androidboilerplate

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.practice.androidboilerplate.databinding.ViemodelPracticeBinding

class ViewModelPractice :AppCompatActivity() {
    private lateinit var binding: ViemodelPracticeBinding
    private lateinit var viewModel: ViewModelPracticeViewModel
    private lateinit var viewModelFactory: ViewmodelPracticeViewModelFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.viemodel_practice)
        viewModelFactory=ViewmodelPracticeViewModelFactory(125)
        viewModel=ViewModelProvider(this,viewModelFactory).get(ViewModelPracticeViewModel::class.java)
        binding.apply {
            textView.text=viewModel.getCurrentcount().toString()
            button3.setOnClickListener{
                textView.text=viewModel.getUpdatedcount(textInputEditText.text.toString().toInt()).toString()
            }

        }

    }
}