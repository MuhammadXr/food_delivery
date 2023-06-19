package xr.muhammad.hammertesttask.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.launch
import xr.muhammad.hammertesttask.api.local.CartEntity
import xr.muhammad.hammertesttask.api.local.FoodEntities
import xr.muhammad.hammertesttask.api.models.Banner
import xr.muhammad.hammertesttask.api.models.Category
import xr.muhammad.hammertesttask.api.models.FeatureMember
import xr.muhammad.hammertesttask.api.models.GeoObject
import xr.muhammad.hammertesttask.api.models.Product
import xr.muhammad.hammertesttask.api.models.Type
import xr.muhammad.hammertesttask.api.models.getBanners
import xr.muhammad.hammertesttask.repository.AppRepository
import xr.muhammad.hammertesttask.ui.screens.home.viewmodel.HomeViewModel
import xr.muhammad.hammertesttask.utils.connectivity_status.ConnectivityObserver
import javax.inject.Inject

@HiltViewModel
class HomeViewModelImpl @Inject constructor(
    private val appRepository: AppRepository,
    private val connection: ConnectivityObserver
) : HomeViewModel, ViewModel() {

    override val locationState = MutableStateFlow(GeoObject(null,null,null,null,null,))
    override val queryLocations = MutableStateFlow<List<FeatureMember>>(emptyList())
    override val allBanners = MutableStateFlow<List<Banner>>(emptyList())
    override val allCategory = MutableStateFlow<List<Category>>(emptyList())

    override val locationError = MutableStateFlow(false)
    override val foodError = MutableStateFlow(false)

    private lateinit var work:Job


    override fun getLocations(search: String) {
        if (::work.isInitialized && work.isActive){
            work.cancel()
        }

        work = viewModelScope.launch(Dispatchers.IO) {
            delay(200)
            val locations = appRepository.searchLocation(search)

            if (locations != null) {
                locations.response?.GeoObjectCollection?.featureMember?.let {
                    Log.d("TTT", "QUERY SIZE ${it.size}")
                    queryLocations.value = it
                }
            } else {
                locationError.value = true
            }
        }

    }

    override fun getAllFoodData() {
        viewModelScope.launch(Dispatchers.IO) {
            val foods = appRepository.getAllFoodData()
            if (foods != null) {

                foods.categories?.let {
                    allCategory.value = it
                    saveFoodsToLocal()
                }
            }
        }
    }

    override fun setLocation(geoObject: GeoObject) {
        locationState.value = geoObject
    }

    override fun addToCart(product: Product) {
        viewModelScope.launch(Dispatchers.IO){
            appRepository.insertCartToLocal(CartEntity(product))
        }
    }

    init {
        allBanners.value = getBanners()
        if (connection.isNetworkAvailable()){
            getAllFoodData()
        }else{
            viewModelScope.launch(Dispatchers.IO) {
                appRepository.readFoodsFromLocal().take(1).collectLatest { list ->
                    allCategory.value = list.category
                }
            }
        }

    }

    private fun saveFoodsToLocal(){

        viewModelScope.launch(Dispatchers.IO) {
            val foodEntities = FoodEntities(
                category = allCategory.value
            )
            appRepository.insertFoodsToLocal(foodEntities = foodEntities)
        }

    }

}