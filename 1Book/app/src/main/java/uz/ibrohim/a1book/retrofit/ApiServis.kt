package uz.ibrohim.retrofitapi.retrofit

import Preferences
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import uz.ibrohim.a1book.model.GetUserData

interface ApiServis {

    @GET("api/books/{id}")
    suspend fun getData(
        @Header("Authorization") token: String = "Bearer " + Preferences.token,
        @Path("id") id: String
    ): Response<GetUserData>

}

