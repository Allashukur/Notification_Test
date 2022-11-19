package uz.ibrohim.a1book.internet

import android.app.Dialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast
import uz.ibrohim.a1book.R
import uz.ibrohim.a1book.offline.OfflineActivity
import java.io.IOException

class OfflineReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val dialog = Dialog(context)
        val soundEngine = CheckInternet()
        val status: String = soundEngine.getNetworkInfo(context)
        if (status == "disconnected") {
            dialog.setContentView(R.layout.offline_item)
            dialog.setCanceledOnTouchOutside(false)
            dialog.window!!.setLayout(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT
            )
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.window!!.attributes.windowAnimations = android.R.style.Animation_Dialog
            val button = dialog.findViewById<Button>(R.id.btn_internet)
            button.setOnClickListener {
                val intent1 = Intent(context, OfflineActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                context.startActivity(intent1)
            }
            dialog.show()
        } else if (status == "connected") {
            Toast.makeText(context, "Online", Toast.LENGTH_SHORT).show()
        }
    }
}