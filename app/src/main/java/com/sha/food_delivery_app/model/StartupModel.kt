package com.sha.food_delivery_app.model

import java.io.Serializable

/**
 * Created by Shahul Hameed Shaik on 14/12/2020.
 */
data class StartupModel (
    val id: Long,
    val promotionsList: List<String>,
    val availableFoodItemsList: List<String>
) : Serializable