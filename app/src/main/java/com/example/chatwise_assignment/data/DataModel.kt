package com.example.chatwise_assignment.data

import com.google.gson.annotations.SerializedName


data class DataModel(
    @SerializedName("products") var products: ArrayList<Products> = arrayListOf(),
    @SerializedName("total") var total: Int? = null,
    @SerializedName("skip") var skip: Int? = null,
    @SerializedName("limit") var limit: Int? = null

)