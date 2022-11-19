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
import uz.ibrohim.a1book.HomeActivity
import uz.ibrohim.a1book.R
import java.io.IOException

class OnlineReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val checkInternet = CheckInternet();
        val status: String = checkInternet.getNetworkInfo(context)
        if (status == "disconnected") {
            Toast.makeText(context, "Offline rejimi", Toast.LENGTH_SHORT).show()
        } else if (status == "connected") {
            val dialog = Dialog(context)
            dialog.setContentView(R.layout.online_item)
            dialog.setCanceledOnTouchOutside(false)
            dialog.window!!.setLayout(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT
            )
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.window!!.attributes.windowAnimations = android.R.style.Animation_Dialog
            val button = dialog.findViewById<Button>(R.id.btn_online_internet)
            button.setOnClickListener {
                val intent1 = Intent(context, HomeActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                context.startActivity(intent1)
            }
            dialog.show()
        }
    }
}