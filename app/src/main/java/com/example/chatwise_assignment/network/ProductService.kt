package com.example.chatwise_assignment.network

import com.example.chatwise_assignment.data.DataModel
import retrofit2.Call
import retrofit2.http.GET

//When you have only one method in interface use fun interface
fun interface ProductService {

    @GET("products")
    fun getProducts(): Call<DataModel>
}
