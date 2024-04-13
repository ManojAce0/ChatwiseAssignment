package com.example.chatwise_assignment.ui.products

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatwise_assignment.data.DataModel
import com.example.chatwise_assignment.databinding.ActivityProductsBinding
import com.example.chatwise_assignment.network.ProductService
import com.example.chatwise_assignment.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductsBinding
    private lateinit var productAdapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        productAdapter = ProductAdapter()
        binding.recyclerView.adapter = productAdapter

        fetchProducts()
    }

    private fun fetchProducts() {
        val service = RetrofitClient.retrofitInstance.create(ProductService::class.java)
        val call = service.getProducts()

        call.enqueue(object : Callback<DataModel> {
            override fun onResponse(call: Call<DataModel>, response: Response<DataModel>) {
                if (response.isSuccessful) {
                    val productList = response.body()
                    productList?.let {
                        productAdapter.submitList(it.products.toMutableList())
                        Log.d("ProductsActivity", "Products fetched successfully: ${it.products.size} products")
                    } ?: run {
                        Log.d("ProductsActivity", "No products found")
                        Toast.makeText(this@ProductsActivity, "No products found", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Log.e("ProductsActivity", "Failed to fetch products: ${response.code()}")
                    Toast.makeText(this@ProductsActivity, "Failed to fetch products", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<DataModel>, t: Throwable) {
                Log.e("ProductsActivity", "Error: ${t.message}", t)
                Toast.makeText(this@ProductsActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }

        })
    }
}
