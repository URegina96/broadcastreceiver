package com.example.broadcastreceiver.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.location.Location
import android.location.LocationManager

class LocationBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val location =
            intent.getParcelableExtra<Location>(LocationManager.KEY_LOCATION_CHANGED)
        if (location != null) {
            // Логика обработки местоположения
        }
    }
}
