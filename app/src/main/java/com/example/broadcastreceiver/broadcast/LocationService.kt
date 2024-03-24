package com.example.broadcastreceiver.broadcast

import android.Manifest
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.os.IBinder
import androidx.core.app.ActivityCompat

class LocationService : Service() {
    private var locationManager: LocationManager? = null
    private var locationListener: LocationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) {
            val locationIntent = Intent("com.example.location_updates")
            locationIntent.putExtra("latitude", location.latitude)
            locationIntent.putExtra("longitude", location.longitude)
            sendBroadcast(locationIntent)
        }

        override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
        }

//        override fun onProviderEnabled(provider: String?) {
//        }
//
//        override fun onProviderDisabled(provider: String?) {
//        }
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ){
            return START_NOT_STICKY
        }

        locationManager?.requestLocationUpdates(
            LocationManager.GPS_PROVIDER,
            0,
            0f,
            locationListener
        )
        return START_STICKY
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        locationManager?.removeUpdates(locationListener)
    }
}