package xr.muhammad.hammertesttask.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import xr.muhammad.hammertesttask.api.models.Banner
import xr.muhammad.hammertesttask.api.models.Category
import xr.muhammad.hammertesttask.api.models.FeatureMember
import xr.muhammad.hammertesttask.api.models.GeoObject
import xr.muhammad.hammertesttask.repository.AppRepository
import xr.muhammad.hammertesttask.ui.screens.home.viewmodel.HomeViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModelImpl @Inject constructor(
    private val appRepository: AppRepository
): HomeViewModel, ViewModel() {

    override val locationState = MutableStateFlow(GeoObject())
    override val queryLocations = MutableStateFlow<List<FeatureMember>>(emptyList())
    override val allBanners = MutableStateFlow<List<Banner>>(emptyList())
    override val allCategory = MutableStateFlow<List<Category>>(emptyList())

    override val locationError = MutableStateFlow(false)
    override val foodError = MutableStateFlow(false)


    override fun getLocations(search: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val locations = appRepository.searchLocation(search)
            if (locations != null){
                locations.response?.geoObjectCollection?.featureMember?.let {
                    queryLocations.value = it
                }
            }else{
                locationError.value = true;
            }
        }

    }

    override fun getAllFoodData() {
        viewModelScope.launch(Dispatchers.IO) {
            val foods = appRepository.getAllFoodData()
            if (foods != null){
                foods.pageProps?.banners?.let {
                    allBanners.value = it
                }
                foods.pageProps?.categories?.let {
                    allCategory.value = it
                }
            }
        }
    }

    override fun setLocation(geoObject: GeoObject) {
        locationState.value = geoObject
    }

    init {
        getAllFoodData()
    }

}