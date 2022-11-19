package uz.ibrohim.retrofitapi.retrofit

import com.chuckerteam.chucker.api.ChuckerInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.ibrohim.a1book.App

object ApiClient {

    private const val BASE_URL = "https://api.1books.kadirov.org/"

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }
    var client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(ChuckerInterceptor(App.instance))
        .build()

    val apiServis: ApiServis = getRetrofit().create(ApiServis::class.java)
}