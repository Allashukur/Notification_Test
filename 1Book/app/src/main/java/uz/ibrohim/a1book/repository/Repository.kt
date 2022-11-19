package uz.ibrohim.a1book.repository

import uz.ibrohim.retrofitapi.retrofit.ApiServis


class Repository(private val apiServis: ApiServis) {

    suspend fun getUserData(id: String) = apiServis.getData(id = id)

}