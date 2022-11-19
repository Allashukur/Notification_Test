package uz.ibrohim.a1book

import NetworkHelper
import Preferences
import android.annotation.SuppressLint
import android.app.Activity
import android.content.BroadcastReceiver
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import uz.ibrohim.a1book.internet.OfflineReceiver
import uz.ibrohim.a1book.javaScript.CallTokenId
import uz.ibrohim.a1book.javaScript.JavaScriptInterface
import uz.ibrohim.a1book.resource.Resource
import uz.ibrohim.a1book.viewModel.ViewModel
import uz.ibrohim.retrofitapi.retrofit.ApiClient
import kotlin.coroutines.CoroutineContext

class HomeActivity() : AppCompatActivity(), CallTokenId, CoroutineScope {
    private lateinit var webView: WebView
    private var broadcastReceiver: BroadcastReceiver? = null

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        Preferences.init(this)
        broadcastReceiver = OfflineReceiver()
        internetCheck()

        webView = findViewById(R.id.wb_webView)
        webView.webViewClient = WebViewClient()
        webView.apply {
            settings.javaScriptEnabled = true
            settings.loadWithOverviewMode = true
            settings.databaseEnabled = true
            webView.settings.domStorageEnabled = true
            loadUrl("https://1books.kadirov.org")
            addJavascriptInterface(
                JavaScriptInterface(context as Activity, this@HomeActivity),
                "Android"
            )
        }
    }

    override fun loadId(ID: String) {
        Toast.makeText(this, ID, Toast.LENGTH_SHORT).show()
        requestData(id = ID)
    }

    override fun loadToken(token: String) {
        Preferences.token = token
    }

    private fun requestData(id: String) {
        val networkHelper = NetworkHelper(this)
        val viewModel = ViewModel(ApiClient.apiServis, networkHelper)
        viewModel.getUserData(id)

        launch {
            viewModel.flow.collect {
                when (it) {
                    is Resource.Error -> {
                        Log.d("test777", it.message)
                    }
                    Resource.Loading -> {
                        Log.d("test777", "Loading..")
                    }
                    is Resource.Success -> {
                        Log.d("test777", "${it.data}")
                        Toast.makeText(this@HomeActivity, "${it.data}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun internetCheck() {
        registerReceiver(broadcastReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(broadcastReceiver)
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + Job()


}