package com.example.shop.ui.list

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.shop.databinding.RowProductListBinding

class ListRecyclerAdapter(private val productList: LiveData<MutableList<AvailableProduct>>)  : RecyclerView.Adapter<ListRecyclerAdapter.ProductViewHolder>() {

    val TAG = this.javaClass.canonicalName

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val itemBinding = RowProductListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holderProduct: ProductViewHolder, position: Int) {
        holderProduct.bind(productList,position)
    }

    override fun getItemCount(): Int = if(productList.value?.size==null) 0 else productList.value?.size!!

    class ProductViewHolder(private val itemBinding: RowProductListBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(availableProductsLD: LiveData<MutableList<AvailableProduct>>, position: Int) {
            if(availableProductsLD.value!=null){
                var product = availableProductsLD.value!!.get(position)
                itemBinding.subButton.isEnabled = product.amountToBuy>0
                itemBinding.addButton.isEnabled = product.amountToBuy<product.amount
                val text = product.nameOfProduct + " amount:" + product.amount + " "+product.price+"USD"
                itemBinding.nameTextView.text = text
                        itemBinding.amountTextView.text = product.amountToBuy.toString()
                itemBinding.addButton.setOnClickListener {
                    product.amountToBuy = product.amountToBuy+1
                    updateButtons(product)
                    Log.d("ListRecyclerAdapter",availableProductsLD.value.toString())
                }
                itemBinding.subButton.setOnClickListener {
                    product.amountToBuy = product.amountToBuy-1
                    updateButtons(product)
                    Log.d("ListRecyclerAdapter",availableProductsLD.value.toString())
                }
            }

        }

        private fun updateButtons(product: AvailableProduct) {
            itemBinding.addButton.isEnabled = product.amountToBuy<product.amount
            itemBinding.subButton.isEnabled = product.amountToBuy>0
            itemBinding.amountTextView.text = product.amountToBuy.toString()
        }
    }


}

