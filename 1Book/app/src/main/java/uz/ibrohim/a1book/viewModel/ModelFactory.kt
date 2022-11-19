package uz.ibrohim.a1book.viewModel
import NetworkHelper
import androidx.lifecycle.ViewModel
import uz.ibrohim.retrofitapi.retrofit.ApiClient.apiServis
import uz.ibrohim.retrofitapi.retrofit.ApiServis

class ModelFactory(
    val networkHelper : NetworkHelper,
    val apiServis: ApiServis)
//) : ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(MoveViewModel::class.java)) {
//            return MoveViewModel(apiServis, networkHelper) as T
//        }
//        throw  RuntimeException("Error")
//    }
//

