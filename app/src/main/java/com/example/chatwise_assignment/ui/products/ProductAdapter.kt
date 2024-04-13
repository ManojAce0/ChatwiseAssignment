package com.example.chatwise_assignment.ui.products


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.chatwise_assignment.data.Products
import com.example.chatwise_assignment.databinding.ProductItemBinding

class ProductAdapter :
    ListAdapter<Products, ProductAdapter.ProductViewHolder>(ProductDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = getItem(position)
        holder.bind(product)
    }

    inner class ProductViewHolder(private val binding: ProductItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Products) {
            binding.productNameTextView.text = product.title
            binding.productPriceTextView.text = "$${product.price}"
            binding.productDescriptionTextView.text = product.description
            binding.productDiscountTextView.text = "Discount: ${product.discountPercentage}%"
            binding.productRatingTextView.text = "Rating: ${product.rating}"
            binding.productStockTextView.text = "Stock: ${product.stock}"
            binding.productBrandTextView.text = "Brand: ${product.brand}"
            binding.productCategoryTextView.text = "Category: ${product.category}"

            Glide.with(binding.root)
                .load(product.thumbnail)
                .into(binding.productImageView)
        }
    }
}

class ProductDiffCallback : DiffUtil.ItemCallback<Products>() {
    override fun areItemsTheSame(oldItem: Products, newItem: Products): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Products, newItem: Products): Boolean {
        return oldItem == newItem
    }
}
