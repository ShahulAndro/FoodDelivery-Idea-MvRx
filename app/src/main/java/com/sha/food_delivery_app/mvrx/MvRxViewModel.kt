package com.sha.food_delivery_app.mvrx

import com.airbnb.mvrx.BaseMvRxViewModel
import com.airbnb.mvrx.MavericksState

open class MvRxViewModel<S : MavericksState>(state: S) : BaseMvRxViewModel<S>(state)
