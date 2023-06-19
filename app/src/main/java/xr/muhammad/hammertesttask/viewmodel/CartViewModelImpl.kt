package xr.muhammad.hammertesttask.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import xr.muhammad.hammertesttask.api.local.CartEntity
import xr.muhammad.hammertesttask.repository.AppRepository
import xr.muhammad.hammertesttask.ui.screens.cart.viewmodel.CartViewModel
import javax.inject.Inject

@HiltViewModel
class CartViewModelImpl @Inject constructor(
    private val appRepository: AppRepository
) : ViewModel(), CartViewModel {
    override val allCarts = MutableStateFlow<List<CartEntity>>(emptyList())

    override fun getAllCarts() {
        viewModelScope.launch(Dispatchers.IO) {
            appRepository.readCartsFromLocal().collectLatest {
                allCarts.value = it
            }
        }
    }

    override fun orderAllCarts() {
        viewModelScope.launch(Dispatchers.IO) {
            appRepository.deleteAllCarts()
        }
    }

    init {
        getAllCarts()
    }
}