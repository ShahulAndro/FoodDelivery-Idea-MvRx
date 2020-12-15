package com.sha.food_delivery_app.data.repository

import com.sha.food_delivery_app.model.StartupModel
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

/**
 * Created by Shahul Hameed Shaik on 14/12/2020.
 */
class MainDataRepository {
    fun getStarupData() = Observable.fromCallable<StartupModel> {
        Thread.sleep(2000)
        StartupModel(
            1,
            listOf(
                "android.resource://com.sha.food_delivery_app/raw/pizza_promo1",
                "android.resource://com.sha.food_delivery_app/raw/pizza_promo2",
                "android.resource://com.sha.food_delivery_app/raw/pizza_promo3",
                "android.resource://com.sha.food_delivery_app/raw/sushi_promo1",
                "android.resource://com.sha.food_delivery_app/raw/sushi_promo2",
                "android.resource://com.sha.food_delivery_app/raw/drinks_promo1",
                "android.resource://com.sha.food_delivery_app/raw/drinks_promo2"
            ),
            listOf(
                "Pizza",
                "Sushi",
                "Drinks"
            )
        )
    }.subscribeOn(Schedulers.io())
}