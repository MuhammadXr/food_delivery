package xr.muhammad.hammertesttask.viewmodel

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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
): HomeViewModel {
    override val locationState: StateFlow<GeoObject> = MutableStateFlow(GeoObject())
    override val queryLocations: StateFlow<FeatureMember> = MutableStateFlow(FeatureMember())
    override val allBanners: StateFlow<List<Banner>> = MutableStateFlow(emptyList())
    override val allCategory: StateFlow<List<Category>> = MutableStateFlow(emptyList())

    override fun getLocations(search: String) {

    }

    override fun getAllFoodData() {

    }
}