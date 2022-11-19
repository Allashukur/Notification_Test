package uz.ibrohim.a1book.resource

import uz.ibrohim.a1book.model.GetUserData


sealed class Resource {

    object Loading : Resource()
    data class Success(val data: GetUserData) : Resource()
    data class Error(val message: String) : Resource()

}
