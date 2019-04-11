package com.example.androidthings.testapp

import android.app.Activity
import android.os.Bundle
import android.util.Log
import com.example.androidthings.testapp.singleton.LedSingleton
import com.example.androidthings.testapp.singleton.SerialSingleton
import utils.TAG

class MainActivity : Activity() {
    private lateinit var restFulService: RESTfulService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "Starting HomeActivity")
        // Init LedSingleton
        Log.i(TAG, "Init pin")
        LedSingleton
        // Launch server
        Log.i(TAG, "Init server")
        val restFulService = RESTfulService()
        restFulService.serverStatus(this,true)
        // On click
        Log.i(TAG, "Button click")
        LedSingleton.gpioButton.registerGpioCallback { LedSingleton.changeState(); true}
        // UART
        Log.i(TAG,"open UART")
        SerialSingleton
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "Closing Button GPIO pin")
        LedSingleton.gpioButton.close()
        restFulService.serverStatus(this,false)
    }
}
