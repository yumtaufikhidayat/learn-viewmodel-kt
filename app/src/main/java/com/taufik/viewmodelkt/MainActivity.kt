package com.taufik.viewmodelkt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.taufik.viewmodelkt.databinding.ActivityMainBinding
import com.taufik.viewmodelkt.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setViewModel()

        initView()

        displayResult()
    }

    private fun setViewModel() {
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
    }

    private fun initView() {

        binding.apply {

            btnCalculate.setOnClickListener {
                val width = etWidth.text.toString()
                val height = etHeight.text.toString()
                val length = etLength.text.toString()

                binding.apply {
                    when {
                        width.isEmpty() -> {
                            etWidth.error = "Masih kosong"
                        }

                        height.isEmpty() -> {
                            etHeight.error = "Masih kosong"
                        }

                        length.isEmpty() -> {
                            etLength.error = "Masih kosong"
                        }

                        else -> {
                            viewModel.calculate(width, height, length)
                            displayResult()
                        }
                    }
                }
            }
        }
    }

    private fun displayResult() {
        binding.apply {
            tvResult.text = viewModel.result.toString()
        }
    }
}