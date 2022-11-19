package uz.ibrohim.a1book.offline

import android.content.BroadcastReceiver
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import uz.ibrohim.a1book.R
import uz.ibrohim.a1book.internet.OnlineReceiver

class OfflineActivity : AppCompatActivity() {
    var broadcastReceiver: BroadcastReceiver? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_offline)

        broadcastReceiver = OnlineReceiver()
        InternetStatus()
    }

    fun InternetStatus() {
        registerReceiver(broadcastReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(broadcastReceiver)
    }
}