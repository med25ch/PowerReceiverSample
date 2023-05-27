package com.example.powerreceiversample

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.util.logging.Filter

class MainActivity : AppCompatActivity() {


    private val customReceiver = CustomReceiver()
    private lateinit var filter: IntentFilter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        filter = IntentFilter()

        filter.addAction(Intent.ACTION_POWER_DISCONNECTED)
        filter.addAction(Intent.ACTION_POWER_CONNECTED)

        // Register the receiver using the activity context.
        this.registerReceiver(customReceiver,filter)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onDestroy() {
        this.unregisterReceiver(customReceiver)
        super.onDestroy()
    }
}