package com.example.sqllite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputName : EditText = findViewById(R.id.inputName)
        val inputQuantity : EditText = findViewById(R.id.inputQuantity)
        val inputPrice : EditText = findViewById(R.id.inputPrice)
        val buttonSave : Button = findViewById(R.id.buttonSave)
        val buttonShow : Button = findViewById(R.id.buttonShow)
        val buttonDelete : Button = findViewById(R.id.buttonDelete)
        val buttonFetchOneProduct : Button = findViewById(R.id.buttonFetchOneProduct)

        //initializing the database
        val db  = DBHelper(this, null)

        // SAVING THE DATA
        buttonSave.setOnClickListener {


            val name = inputName.text.toString().trim()
            val quantity = inputQuantity.text.toString().trim().toString().toIntOrNull()
            val price = inputPrice.text.toString().trim().toIntOrNull()

            if (quantity != null && price != null && name.isNotEmpty()) {
                //saving to database
                db.addProducts(name, price, quantity)
                Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show()

                //clear input
                inputName.text.clear()
                inputQuantity.text.clear()
                inputPrice.text.clear()

            }else {
                Toast.makeText(this, "You entered an invalid character", Toast.LENGTH_SHORT).show()
            }
        }

        // FETCHING THE DATA
        buttonShow.setOnClickListener {
            /*val products = db.getProducts()//CURSOR
            while (products!!.moveToNext()) {
               *//* COLUMNS
                ID -> 0
                Name -> 1
                Price -> 2
                Quantity -> 3*//*
                val id = products.getInt(0)
                val name = products.getString(1)
                val price = products.getString(2)
                val quantity = products.getString(3)
                Log.d("PRODUCT", "$id: $name: $price: $quantity")
                //Toast.makeText(this, "$id: $name: $price: $quantity", Toast.LENGTH_SHORT).show()*/
            val intent = Intent(this, DisplayActivity::class.java)
            startActivity(intent)

            }
            db.close()
        }

       /* // DELETING THE DATA
        buttonDelete.setOnClickListener {
            //db.deleteProduct(1)
            //UPDATE
            //db.updateProduct(3, "HP", 23000, 56)

            //SEARCH
            val result = db.searchProduct("HP")
            val count = result!!.count
            Log.d("PRODUCT", "Found $count products")

        }

        //FETCHING ONE PRODUCT
        buttonFetchOneProduct.setOnClickListener {
            val p = db.fetchOneProduct(2)
            p!!.moveToFirst()
            val name = p.getString(1)
            Toast.makeText(this, name, Toast.LENGTH_SHORT).show()
        }

        //Hiding buttons
        buttonDelete.visibility = View.GONE
*/
    }
