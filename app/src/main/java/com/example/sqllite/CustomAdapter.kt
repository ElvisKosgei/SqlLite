package com.example.sqllite

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(private val productsList: List<Products>) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = productsList[position]
        holder.productName.text = product.name
        holder.productQuantity.text = product.quantity.toString()
        holder.productPrice.text = product.price.toString()

        holder.itemView.setOnClickListener {
            //Toast.makeText(holder.itemView.context, user.name, Toast.LENGTH_SHORT).show()
            //Go to update page
            val intent = Intent(holder.itemView.context, UpdateActivity::class.java)
            intent.putExtra("id", product.id)
            holder.itemView.context.startActivity(intent)

        }
    }

    override fun getItemCount(): Int {
        return productsList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productName: TextView = itemView.findViewById(R.id.txtName)
        val productQuantity: TextView = itemView.findViewById(R.id.txtQuantity)
        val productPrice: TextView = itemView.findViewById(R.id.txtPrice)
    }

}