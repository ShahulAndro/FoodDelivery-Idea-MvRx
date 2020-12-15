package com.sha.food_delivery_app.data.repository

import com.sha.food_delivery_app.model.FoodItemModel
import com.sha.food_delivery_app.model.MainFoodItemModel
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

/**
 * Created by Shahul Hameed Shaik on 14/12/2020.
 */
class MainFoodItemDataRepository {
    private val mainFoodItemsData = mutableListOf<MainFoodItemModel>()
    fun getMainFoodItemData() = Observable.fromCallable<List<MainFoodItemModel>> {
        Thread.sleep(2000)
        mainFoodItemsData.addAll(listOf(
            MainFoodItemModel(
                100,
                "Pizza",
                listOf(
                    "Spice",
                    "Vegan",
                    "Chicken",
                    "SeaFood"
                ),
                listOf(
                    FoodItemModel(
                        1000,
                        "android.resource://com.sha.food_delivery_app/raw/pizza1",
                        "CHICKENSAURUS",
                        "Roasted Chicken, Chicken Pepperoni and Mushroom Slices on our awesome Smoky Blended BBQ Sauce",
                        "150 grams",
                        "35cm",
                        "20 USD",
                        false
                    ),
                    FoodItemModel(
                        1001,
                        "android.resource://com.sha.food_delivery_app/raw/pizza2",
                        "ULTIMATE HAWAIIAN",
                        "Loads of delicious roasted chicken, shredded chicken juicy pineapples and fresh mushrooms on our brand new pizza.",
                        "100 grams",
                        "25cm",
                        "15 USD",
                        false
                    ),
                    FoodItemModel(
                        1002,
                        "android.resource://com.sha.food_delivery_app/raw/pizza3",
                        "PERI-PERI CHICKEN",
                        "Roasted Chicken, Chicken Pepperoni and Mushroom Slices on our awesome Smoky Blended BBQ Sauce",
                        "150 grams",
                        "35cm",
                        "25 USD",
                        false
                    ),
                    FoodItemModel(
                        1003,
                        "android.resource://com.sha.food_delivery_app/raw/pizza4",
                        "SUPREME GOURMET",
                        "Roasted Chicken, Chicken Pepperoni and Mushroom Slices on our awesome Smoky Blended BBQ Sauce",
                        "150 grams",
                        "35cm",
                        "30 USD",
                        false
                    ),
                    FoodItemModel(
                        1004,
                        "android.resource://com.sha.food_delivery_app/raw/pizza5",
                        "SAMBAL VEGGIE",
                        "Roasted Chicken, Chicken Pepperoni and Mushroom Slices on our awesome Smoky Blended BBQ Sauce",
                        "150 grams",
                        "35cm",
                        "20 USD",
                        false
                    )
                )
            ),
            MainFoodItemModel(
                200,
                "Sushi",
                listOf(
                    "Japanese",
                    "Korea"
                ),
                listOf(
                    FoodItemModel(
                        2001,
                        "https://dl5zpyw5k3jeb.cloudfront.net/photos/pets/44365525/3/?bust=1554156953&width=1080",
                        "Tokyo Don",
                        "Rice dish topped with prawn tempura, drizzled with homemade ten don sauce.",
                        "1 pack",
                        "40cm",
                        "25 USD",
                        false
                    ),
                    FoodItemModel(
                        2002,
                        "https://cdn3-www.dogtime.com/assets/uploads/gallery/pit-bull-dog-breed-pictures/pit-bull-dog-breed-picture-1.jpg",
                        "Bundle Deals",
                        "Deep-fried salmon and chicken with spicy sauce and mayonnaise, teriyaki chicken patty served ",
                        "1 pack",
                        "15cm",
                        "35 USD",
                        false
                    )
                )
            ),
            MainFoodItemModel(
                300,
                "Drinks",
                listOf(
                    "TEA&COFFEE",
                    "Soft Drinks"
                ),
                listOf(
                    FoodItemModel(
                        3001,
                        "https://dl5zpyw5k3jeb.cloudfront.net/photos/pets/44365525/3/?bust=1554156953&width=1080",
                        "Espresso Cafe",
                        "Sugar, Milk, Fresh coffee beans",
                        "150 grams",
                        "35cm",
                        "5 USD",
                        false
                    ),
                    FoodItemModel(
                        3002,
                        "https://cdn3-www.dogtime.com/assets/uploads/gallery/pit-bull-dog-breed-pictures/pit-bull-dog-breed-picture-1.jpg",
                        "Wake Up Cafe",
                        "Sugar, Milk, Coffee beans",
                        "100 grams",
                        "25cm",
                        "3 USD",
                        false
                    )
                )
            )
            ))

        mainFoodItemsData
    }.subscribeOn(Schedulers.io())

    // fetch method to get food item data
    fun fetchMainFoodItemData(item: String): Observable<MainFoodItemModel> {
        return Observable.fromCallable {
            Thread.sleep(4000)
            val mainFoodItemData = mainFoodItemsData.first { mainFoodItemData -> mainFoodItemData.item == item }
            mainFoodItemData.copy()
        }
    }

    // fetch method to get cart fooditems data
    fun fetchAddedFoodItemsToTheCart(): Observable<List<FoodItemModel>> {
        return Observable.fromCallable {
            Thread.sleep(2000)
            val resultObject = mainFoodItemsData.filter { item ->
                item.items.any { it.isAddedToCart }
            }
            val foodItems = resultObject?.flatMap { it.items }.toList()
            foodItems
        }
    }

    // add method to add a fooditem to cart
    fun addCartFoodItems(itemId: Long): Observable<FoodItemModel> {
        return Observable.fromCallable {
            val resultObject = mainFoodItemsData.find { item ->
                item.items.any { it.id != null && it.id == itemId }
            }
            val foodItem = resultObject?.items?.find { it.id == itemId }
            foodItem?.copy(isAddedToCart = true)
        }
    }

    // add method to remove a fooditem from cart
    fun removeFoodItemFromCart(itemId: Long): Observable<FoodItemModel> {
        return Observable.fromCallable {
            val resultObject = mainFoodItemsData.find { item ->
                item.items.any { it.id != null && it.id == itemId }
            }
            val foodItem = resultObject?.items?.find { it.id == itemId }
            foodItem?.copy(isAddedToCart = false)
        }
    }
}