package com.sha.food_delivery_app.viewmodel

import androidx.lifecycle.MutableLiveData
import com.airbnb.mvrx.*
import com.sha.food_delivery_app.FoodDeliveryApplication
import com.sha.food_delivery_app.data.repository.MainFoodItemDataRepository
import com.sha.food_delivery_app.mvrx.MvRxViewModel
import com.sha.food_delivery_app.state.FoodItemState
import io.reactivex.schedulers.Schedulers

/**
 * Created by Shahul Hameed Shaik on 13/12/2020.
 */
class FoodItemsViewModel (
    initialState: FoodItemState,
    private val mainFoodItemDataRepository: MainFoodItemDataRepository
) : MvRxViewModel<FoodItemState>(initialState) {
    val errorMessage = MutableLiveData<String>()

    init {
        setState {
            copy(mainFoodDataItems = Loading())
        }
        mainFoodItemDataRepository.getMainFoodItemData().execute { copy(mainFoodDataItems = it) }
    }

    fun fetchMainFoodItemData(item: String) = withState { state ->
        mainFoodItemDataRepository.fetchMainFoodItemData(item)
            .execute { copy(mainFoodDataItem = it) }
    }

    fun fetchAddedFoodItemsFromCart() = withState { state ->
        mainFoodItemDataRepository.fetchAddedFoodItemsToTheCart()
            .subscribeOn(Schedulers.io())
            .execute { copy(foodItems = it) }
    }

    fun addFoodItemToCart(itemId: Long) {
        withState { state ->
            if (state.mainFoodDataItems is Success) {
                mainFoodItemDataRepository.addCartFoodItems(itemId)
                    .subscribeOn(Schedulers.io())
                    .execute {
                        if (it is Success) {
                            copy(foodItem = it)
                        } else if (it is Fail){
                            errorMessage.postValue("Failed to add food item to cart")
                            copy()
                        } else {
                            copy()
                        }
                    }
            }
        }
    }

    fun removeFoodItemFromCart(itemId: Long) {
        withState { state ->
            if (state.mainFoodDataItems is Success) {
                mainFoodItemDataRepository.removeFoodItemFromCart(itemId)
                    .subscribeOn(Schedulers.io())
                    .execute {
                        if (it is Success) {
                            copy(foodItem = it)
                        } else if (it is Fail){
                            errorMessage.postValue("Failed to remove fooditem from cart")
                            copy()
                        } else {
                            copy()
                        }
                    }
            }
        }
    }

    companion object : MavericksViewModelFactory<FoodItemsViewModel, FoodItemState> {
        override fun create(viewModelContext: ViewModelContext, state: FoodItemState): FoodItemsViewModel? {
            val mainFoodItemDataRepository = viewModelContext.app<FoodDeliveryApplication>().mainFoodItemDataRepository
            return FoodItemsViewModel(
                state,
                mainFoodItemDataRepository
            )
        }
    }
}