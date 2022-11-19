package com.example.myclicktest

import android.content.ClipData
import android.content.ClipboardManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.myclicktest.databinding.ActivityMainBinding
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging


class MainActivity : AppCompatActivity() {

    var _binding: ActivityMainBinding? = null
    val binding get() = _binding!!

    private val TAG = "TAG"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        installBtn()

        subscribeTopics()
    }

    private fun installBtn() {
        binding.copyToken.setOnClickListener {
            val clipboard: ClipboardManager = ContextCompat.getSystemService(this, ClipboardManager::class.java) as ClipboardManager
            val clip: ClipData = ClipData.newPlainText("label", Constants.TOKEN)
            clipboard.setPrimaryClip(clip)
        }
    }

    private fun subscribeTopics() {
        FirebaseMessaging.getInstance().subscribeToTopic("topic1").addOnCompleteListener  { task ->
            var msg = "Subscribed"
            if (!task.isSuccessful) {
                msg = "Subscribe failed"
            }
            Log.d(TAG, msg)
        }
    }
}