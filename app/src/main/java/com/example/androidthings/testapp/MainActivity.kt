package com.example.androidthings.testapp

import android.app.Activity
import android.os.Bundle
import android.util.Log
import utils.TAG

class MainActivity : Activity() {
    private lateinit var restFulService: RESTfulService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "Starting HomeActivity")
        // Init Singleton
        Log.i(TAG, "Init pin")
        Singleton
        // Launch server
        Log.i(TAG, "Init server")
        val restFulService = RESTfulService()
        restFulService.serverStatus(this,true)
        // On click
        Log.i(TAG, "Button click")
        Singleton.gpioButton.registerGpioCallback {Singleton.changeState(); true}
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "Closing Button GPIO pin")
        Singleton.gpioButton.close()
        restFulService.serverStatus(this,false)
    }
}
