package com.example.sqllite

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog


class UpdateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)
        val productName : EditText = findViewById(R.id.productName)
        val productPrice : EditText = findViewById(R.id.productPrice)
        val productQuantity : EditText = findViewById(R.id.productQuantity)
        val buttonUpdate : Button = findViewById(R.id.buttonUpdate)
        val buttonDelete : Button = findViewById(R.id.buttonDeleteItem)

        val id = intent.getIntExtra("id", 0)
        val db = DBHelper(this, null)
        val cursor = db.fetchOneProduct(id)
        cursor!!.moveToFirst()

        productName.setText(cursor.getString(1))
        productPrice.setText(cursor.getString(2))
        productQuantity.setText(cursor.getString(3))
        // Updating
        buttonUpdate.setOnClickListener {
            //Update a product

            val name = productName.text.toString().trim()
            val price = productPrice.text.toString().trim().toIntOrNull()
            val quantity = productQuantity.text.toString().trim().toIntOrNull()

            if (name.isNotEmpty() && price != null && quantity != null) {
                db.updateProduct(id,name,price,quantity)
                Toast.makeText(this, "Product $name has been updated", Toast.LENGTH_SHORT).show()
             //navigate to previous page
             //finish()
            }else {
                Toast.makeText(this, "Please enter valid values", Toast.LENGTH_SHORT).show()
            }


        }
        // Deleting
        buttonDelete.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Delete Product")
            builder.setMessage("Deleting is a permanent task. Do you want to proceed?")
            builder.setPositiveButton("Cancel") {dialog,_,->
                dialog.dismiss()
            }
            builder.setNegativeButton("Delete") {_,_ ->
                db.deleteProduct(id)
                Toast.makeText(this, "Item Deleted", Toast.LENGTH_SHORT).show()
                finish()
            }
            builder.show()
        }
    }
}