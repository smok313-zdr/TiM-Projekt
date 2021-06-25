package com.example.shop.ui.list

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shop.ui.addproduct.AddProductActivity
import com.example.shop.data.Basket
import com.example.shop.databinding.ActivityListBinding
import com.example.shop.ui.checkout.BasketProduct
import com.example.shop.ui.checkout.CheckoutActivity

class ListActivity : AppCompatActivity() {
    private lateinit var listViewModel: ListViewModel
    private lateinit var binding: ActivityListBinding


    override fun onResume() {
        super.onResume()
        listViewModel.refreshProductList()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val buyButton = binding.buyButton
        val refreshButton = binding.refreshButton
        val productsRecyclerView = binding.productsRecyclerView
        val addProductButton = binding.addProductButton


        val token = getIntent().getExtras()?.getString("Token")
        val isAdmin = getIntent().getExtras()?.getBoolean("IsAdmin")
        listViewModel = ViewModelProvider(this, ListViewModelFactory(token))
            .get(ListViewModel::class.java)


        listViewModel.productListState.observe(this@ListActivity, Observer {
            productsRecyclerView.adapter = ListRecyclerAdapter(listViewModel.productListState,listViewModel,this@ListActivity)
        })
        productsRecyclerView.layoutManager = LinearLayoutManager(this)
        productsRecyclerView.adapter = ListRecyclerAdapter(listViewModel.productListState,listViewModel,this@ListActivity)
        listViewModel.refreshProductList()
        refreshButton.setOnClickListener{
            listViewModel.refreshProductList()

        }
        buyButton.setOnClickListener {
            val basket =  listViewModel.getProductsForBasket()
            if (basket != null) {
                if(basket.size>0){
                    Toast.makeText(
                        applicationContext,
                        "Basket size"+basket.size,
                        Toast.LENGTH_LONG
                    ).show()
                    setResult(Activity.RESULT_OK)
                    val intent = Intent(this, CheckoutActivity::class.java).apply {
                        putExtra("Token", token)
                        Basket.basket = basket.map { BasketProduct(
                            it.id,
                            it.nameOfProduct,
                            it.price,
                            it.amountToBuy,
                            it.picture
                        ) }
                    }
                    startActivity(intent)
                }
                else{
                    Toast.makeText(
                        applicationContext,
                        "Empty basket",
                        Toast.LENGTH_LONG
                    ).show()
                }

            }
            else{
                Toast.makeText(
                    applicationContext,
                    "Basket is null",
                    Toast.LENGTH_LONG
                ).show()
            }

        }

        if(isAdmin == true){
            addProductButton.visibility = View.VISIBLE
            addProductButton.setOnClickListener {
                val intent = Intent(this, AddProductActivity::class.java).apply {
                    putExtra("Token", token)
                }
                startActivity(intent)
            }
        }



    }
}