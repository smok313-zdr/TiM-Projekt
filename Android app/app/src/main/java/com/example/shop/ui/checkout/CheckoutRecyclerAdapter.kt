package com.example.shop.ui.checkout

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shop.databinding.RowCheckoutListBinding
import com.example.shop.ui.list.AvailableProduct

class CheckoutRecyclerAdapter(private val productList: List<BasketProduct>)  : RecyclerView.Adapter<CheckoutRecyclerAdapter.BasketProductViewHolder>() {

    val TAG = this.javaClass.canonicalName

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketProductViewHolder {
        val itemBinding = RowCheckoutListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BasketProductViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holderProduct: BasketProductViewHolder, position: Int) {
        holderProduct.bind(productList[position])
    }

    override fun getItemCount(): Int = productList.size

    class BasketProductViewHolder(private val itemBinding: RowCheckoutListBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(basketProduct: BasketProduct) {
            val text = "amount: "+basketProduct.amount.toString()+" for: "+basketProduct.price+"USD"
            itemBinding.amountTextView.text = text
            itemBinding.nameTextView.text = basketProduct.nameOfProduct
        }
    }


}

