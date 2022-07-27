package com.example.sqllite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DisplayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)

        val recyclerViewProducts : RecyclerView = findViewById(R.id.recyclerProducts)
        val db = DBHelper(this, null)

        val productsList = ArrayList<Products>()

        val cursor = db.getProducts()
        while (cursor!!.moveToNext()){
            val id = cursor.getInt(0)
            val name = cursor.getString(1)
            val price = cursor.getInt(2)
            val quantity = cursor.getInt(3)

            val product = Products(id, name, price, quantity)
            productsList.add(product)
        }
        recyclerViewProducts.layoutManager = LinearLayoutManager(this)
        val adapter = CustomAdapter(productsList)
        recyclerViewProducts.adapter = adapter
    }
}