package com.sha.food_delivery_app

import android.app.Application
import com.airbnb.mvrx.Mavericks
import com.sha.food_delivery_app.data.repository.MainDataRepository
import com.sha.food_delivery_app.data.repository.MainFoodItemDataRepository

/**
 * Created by Shahul Hameed Shaik on 13/12/2020.
 */
class FoodDeliveryApplication : Application() {
    val mainDataRepository = MainDataRepository()
    val mainFoodItemDataRepository = MainFoodItemDataRepository()

    override fun onCreate() {
        super.onCreate()
        Mavericks.initialize(this)
    }
}