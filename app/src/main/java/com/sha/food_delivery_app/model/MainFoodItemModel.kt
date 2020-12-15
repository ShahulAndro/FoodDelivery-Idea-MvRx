package com.sha.food_delivery_app.model

import java.io.Serializable

/**
 * Created by Shahul Hameed Shaik on 14/12/2020.
 */
data class MainFoodItemModel (
    val id: Long,
    val item: String,
    val filterTypes: List<String>,
    val items: List<FoodItemModel>
): Serializable