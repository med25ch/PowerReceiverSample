package com.example.powerreceiversample

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.powerreceiversample.databinding.ActivityMainBinding
import java.util.logging.Filter

class MainActivity : AppCompatActivity() {


    private val customReceiver = CustomReceiver()
    private lateinit var filter: IntentFilter

    companion object {
        private const val ACTION_CUSTOM_BROADCAST = "${BuildConfig.APPLICATION_ID}.ACTION_CUSTOM_BROADCAST"
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        filter = IntentFilter()

        filter.addAction(Intent.ACTION_POWER_DISCONNECTED)
        filter.addAction(Intent.ACTION_POWER_CONNECTED)
        filter.addAction(Intent.ACTION_HEADSET_PLUG)

        // Register the receiver using the activity context.
        this.registerReceiver(customReceiver,filter)



        binding.sendBroadcast.setOnClickListener {
            sendCustomBroadcast()
        }

        //Register for the custom broadcast
        LocalBroadcastManager.getInstance(this)
            .registerReceiver(customReceiver,
                IntentFilter(ACTION_CUSTOM_BROADCAST))

    }

    private fun sendCustomBroadcast() {
        val customBroadcastIntent = Intent(ACTION_CUSTOM_BROADCAST)
        LocalBroadcastManager.getInstance(this).sendBroadcast(customBroadcastIntent)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onDestroy() {
        this.unregisterReceiver(customReceiver)
        LocalBroadcastManager.getInstance(this)
            .unregisterReceiver(customReceiver)
        super.onDestroy()
    }
}