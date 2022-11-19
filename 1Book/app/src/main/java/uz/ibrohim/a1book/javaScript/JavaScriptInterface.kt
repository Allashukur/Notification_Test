package uz.ibrohim.a1book.javaScript

import android.app.Activity
import android.os.Build.ID
import android.util.Log
import android.webkit.JavascriptInterface
import android.widget.Toast
import uz.ibrohim.a1book.HomeActivity

class JavaScriptInterface(private val mActivity: Activity, private val callTokenId: CallTokenId) {

    @JavascriptInterface
    fun showToastBookId(id: String) {
        Toast.makeText(mActivity, id, Toast.LENGTH_SHORT).show()
        callTokenId.loadId(id)
        Log.i("test123", "ID : " + id)
    }

    @JavascriptInterface
    fun showToastBookToken(token: String) {
        Toast.makeText(mActivity, token, Toast.LENGTH_SHORT).show()
        callTokenId.loadToken(token)
        Log.i("test123 ", "Token: " + token)
    }

    @JavascriptInterface
    fun isAndroid(): Boolean {
        return true
    }
}