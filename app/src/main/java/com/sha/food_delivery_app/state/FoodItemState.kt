package com.sha.food_delivery_app.state

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MvRxState
import com.airbnb.mvrx.Uninitialized
import com.sha.food_delivery_app.model.FoodItemModel
import com.sha.food_delivery_app.model.MainFoodItemModel

/**
 * Created by Shahul Hameed Shaik on 13/12/2020.
 */
data class FoodItemState (
    val foodItems: Async<List<FoodItemModel>> = Uninitialized,
    val foodItem: Async<FoodItemModel> = Uninitialized,
    val mainFoodDataItems: Async<List<MainFoodItemModel>> = Uninitialized,
    val mainFoodDataItem: Async<MainFoodItemModel> = Uninitialized,
    val addedFoodItemsId: Long? = null,
    val adoptionRequest: Async<FoodItemModel> = Uninitialized
) : MvRxState {
    val addedFoodItem: FoodItemModel? = foodItem(addedFoodItemsId)

    fun foodItem(addedFoodItemsId: Long?): FoodItemModel? = foodItems()?.firstOrNull { it.id == addedFoodItemsId }
}