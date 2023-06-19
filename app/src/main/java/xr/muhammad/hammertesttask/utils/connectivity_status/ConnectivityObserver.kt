package xr.muhammad.hammertesttask.utils.connectivity_status

import kotlinx.coroutines.flow.Flow

interface ConnectivityObserver {

    fun isNetworkAvailable(): Boolean

    fun observeConnectivity(): Flow<Status>

    enum class Status {
        Available, Unavailable, Losing, Lost
    }
}