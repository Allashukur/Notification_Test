package uz.ibrohim.a1book.internet

import android.content.Context
import android.net.ConnectivityManager

class CheckInternet {

    fun getNetworkInfo(context: Context): String {
        val status: String?
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return if (networkInfo != null) {
            status = "connected"
            status
        } else {
            status = "disconnected"
            status
        }
    }
}