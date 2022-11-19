package uz.ibrohim.a1book.viewModel

import NetworkHelper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import uz.ibrohim.a1book.resource.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import uz.ibrohim.a1book.repository.Repository
import uz.ibrohim.retrofitapi.retrofit.ApiServis
import java.lang.Exception

class ViewModel(
    apiServis: ApiServis,
    private val networkHelper: NetworkHelper,
) : ViewModel() {

    private val moveRepository = Repository(apiServis)

    val flow = MutableStateFlow<Resource>(Resource.Loading)
    fun getUserData(id:String): StateFlow<Resource> {

        viewModelScope.launch {
            try {
                if (networkHelper.isNetworkConnected()) {
                    val response = moveRepository.getUserData(id)
                    if (response.isSuccessful && response.body() != null) {
                        flow.emit(Resource.Success(response.body()!!))
                    } else {
                        flow.emit(Resource.Error("Sever Error !"))
                    }
                } else {
                    flow.emit(Resource.Error("Network no connection !"))
                }
            } catch (e: Exception) {
                flow.emit(Resource.Error(e.message.toString()))
            }
        }
        return flow
    }


}