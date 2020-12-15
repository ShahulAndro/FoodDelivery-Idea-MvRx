package com.sha.food_delivery_app.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.airbnb.mvrx.*
import com.sha.food_delivery_app.viewmodel.FoodItemsViewModel
import com.sha.food_delivery_app.R
import com.sha.food_delivery_app.adapter.FoodItemsAdapter
import kotlinx.android.synthetic.main.fooditems_fragment.*

/**
 * Created by Shahul Hameed Shaik on 13/12/2020.
 */
class FoodItemsFragment : Fragment(), MvRxView {

    private val viewModel: FoodItemsViewModel by activityViewModel()

    private var adapter : FoodItemsAdapter? = null

    private var foodItemName: String? = null

    private var listener: Listener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fooditems_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = FoodItemsAdapter(object : FoodItemsAdapter.Listener {
            override fun addToCart(itemId: Long) {
                viewModel.addFoodItemToCart(itemId)
                listener?.addedToCart()
            }

        })
        foodItemsRecyclerView.adapter = adapter
        viewModel.fetchMainFoodItemData(foodItemName!!)
    }

    override fun invalidate() = withState(viewModel) { state ->
        when (state.mainFoodDataItem) {
            is Loading -> {
                progress_bar.visibility = View.VISIBLE
                foodItemsRecyclerView.visibility = View.GONE
            }
            is Success -> {
                progress_bar.visibility = View.GONE
                foodItemsRecyclerView.visibility = View.VISIBLE
                adapter!!.setFoodItems(state.mainFoodDataItem.invoke().items)
            }
            is Fail -> {
                Toast.makeText(requireContext(), "Failed to load all movies", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        fun newInstance(foodItemName: String, listener: Listener): FoodItemsFragment {
            val fragment = FoodItemsFragment()
            fragment.foodItemName = foodItemName
            fragment.listener = listener
            return fragment
        }
    }

    interface Listener {
        fun addedToCart()
    }

}