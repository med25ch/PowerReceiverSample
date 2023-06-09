package com.example.powerreceiversample

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.widget.Toast

class CustomReceiver : BroadcastReceiver() {

    companion object {
        private const val ACTION_CUSTOM_BROADCAST = "${BuildConfig.APPLICATION_ID}.ACTION_CUSTOM_BROADCAST"
    }

    override fun onReceive(context: Context, intent: Intent) {
        val intentAction = intent.action


        intentAction?.let {
            var toastMessage = "unknown intent action"
            var headSetPlugOnOff = -1
            when(it){
                Intent.ACTION_POWER_CONNECTED -> toastMessage = "Power connected"
                Intent.ACTION_POWER_DISCONNECTED -> toastMessage = "Power disconnected!"
                Intent.ACTION_HEADSET_PLUG -> {
                    headSetPlugOnOff = intent.getIntExtra("state",-1)
                    toastMessage = "HeadSet Plug Received! -- state : $headSetPlugOnOff "
                }
                ACTION_CUSTOM_BROADCAST -> toastMessage = "Custom Broadcast Received"
            }

            Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show()

        }
    }
}