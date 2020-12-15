package com.sha.food_delivery_app.adapter

import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sha.food_delivery_app.R
import com.sha.food_delivery_app.model.FoodItemModel
import com.squareup.picasso.Picasso

/**
 * Created by Shahul Hameed Shaik on 13/12/2020.
 */

class FoodItemsAdapter(private val listener: Listener) : RecyclerView.Adapter<FoodItemsAdapter.FoodViewHolder>() {

    private val foodItems = mutableListOf<FoodItemModel>()

    fun setFoodItems(movies: List<FoodItemModel>) {
        this.foodItems.clear()
        this.foodItems.addAll(movies)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fooditem_row, parent, false)
        return FoodViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val foodItem = foodItems[position]
        holder.foodItemNameTextView.text = foodItem.name
        holder.foodItemIngredientsTextView.text = foodItem.ingredients
        holder.foodItemWeightAndSizeTextView.text = "${foodItem.weight}, ${foodItem.size}"
        holder.priceButton.text = foodItem.price

        Picasso.get().load(foodItem.imageUrl).into(holder.foodItemImageView)

        //Todo: Update this with animation
        holder.priceButton.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    holder.priceButton.text = "Added +1"
                    holder.priceButton.background = holder.priceButton.context.getDrawable(R.drawable.rounded_green_bg)
                }
                MotionEvent.ACTION_UP -> {
                    holder.priceButton.text = foodItem.price
                    holder.priceButton.background = holder.priceButton.context.getDrawable(R.drawable.rounded_black_bg)
                    listener.addToCart(foodItem.id)
                }
                else -> {
                    holder.priceButton.text = foodItem.price
                    holder.priceButton.background = holder.priceButton.context.getDrawable(R.drawable.rounded_black_bg)
                }
            }

            return@setOnTouchListener true
        }
    }

    override fun getItemCount(): Int {
        return foodItems.size
    }

    inner class FoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val foodItemImageView: ImageView = itemView.findViewById(R.id.image)
        val foodItemNameTextView: TextView = itemView.findViewById(R.id.name)
        val foodItemIngredientsTextView: TextView = itemView.findViewById(R.id.ingredients)
        val foodItemWeightAndSizeTextView: TextView = itemView.findViewById(R.id.pizzaWieghtAndSize)
        val priceButton: TextView = itemView.findViewById(R.id.price)
    }

    interface Listener {
        fun addToCart(itemId: Long)
    }
}