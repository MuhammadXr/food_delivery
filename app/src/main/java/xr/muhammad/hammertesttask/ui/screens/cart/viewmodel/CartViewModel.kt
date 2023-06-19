package xr.muhammad.hammertesttask.ui.screens.cart.viewmodel

import kotlinx.coroutines.flow.StateFlow
import xr.muhammad.hammertesttask.api.local.CartEntity

interface CartViewModel {

    val allCarts : StateFlow<List<CartEntity>>

    fun getAllCarts()

    fun orderAllCarts()
}