package com.sha.food_delivery_app.model

import java.io.Serializable

/**
 * Created by Shahul Hameed Shaik on 13/12/2020.
 */
data class FoodItemModel (
    val id: Long,
    val imageUrl: String,
    val name: String,
    val ingredients: String,
    val weight: String,
    val size: String,
    val price: String,
    val isAddedToCart: Boolean
) : IStableItem, Serializable {
    override val stableId = id
}
