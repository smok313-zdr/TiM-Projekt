package com.example.shop.ui.checkout

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.shop.databinding.RowCheckoutListBinding

class CheckoutRecyclerAdapter(private val productList: List<BasketProduct>, private val checkoutViewModel: CheckoutViewModel, private val lifecycleOwner: LifecycleOwner)  : RecyclerView.Adapter<CheckoutRecyclerAdapter.BasketProductViewHolder>() {

    val TAG = this.javaClass.canonicalName

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketProductViewHolder {
        val itemBinding = RowCheckoutListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BasketProductViewHolder(itemBinding, checkoutViewModel, lifecycleOwner)
    }

    override fun onBindViewHolder(holderProduct: BasketProductViewHolder, position: Int) {
        holderProduct.bind(productList[position])
    }

    override fun getItemCount(): Int = productList.size

    class BasketProductViewHolder(private val itemBinding: RowCheckoutListBinding, private val checkoutViewModel: CheckoutViewModel, private val lifecycleOwner:LifecycleOwner) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(basketProduct: BasketProduct) {
            val text = "amount: "+basketProduct.amount.toString()+" for: "+basketProduct.price+"USD"
            itemBinding.amountTextView.text = text
            itemBinding.nameTextView.text = basketProduct.nameOfProduct

            checkoutViewModel.getPicture(basketProduct.picture).observe(lifecycleOwner, Observer {
                itemBinding.imageView.setImageBitmap(it)
            })
        }
    }


}

