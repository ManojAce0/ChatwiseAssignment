package com.example.chatwise_assignment.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.chatwise_assignment.databinding.ActivityMainBinding
import com.example.chatwise_assignment.ui.products.ProductsActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    //private lateinit var productAdapter: ProductAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(this.layoutInflater)
        setContentView(binding.root)
        binding.viewProductsButton.setOnClickListener{
            val intent= Intent(this, ProductsActivity::class.java)
            startActivity(intent)
        }

    }
}