package com.example.powerreceiversample

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.widget.Toast

class CustomReceiver : BroadcastReceiver() {


init {
    var hello = "Hello"
}
    override fun onReceive(context: Context, intent: Intent) {
        val intentAction = intent.action

        intentAction?.let {
            var toastMessage = "unknown intent action"
            when(it){
                Intent.ACTION_POWER_CONNECTED -> toastMessage = "Power connected"
                Intent.ACTION_POWER_DISCONNECTED -> toastMessage = "Power disconnected!"
            }

            Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show()

        }
    }
}