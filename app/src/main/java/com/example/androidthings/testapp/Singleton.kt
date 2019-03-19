package com.example.androidthings.testapp

import android.util.Log
import com.google.android.things.pio.Gpio
import com.google.android.things.pio.PeripheralManager
import utils.PIN_BUTTON
import utils.PIN_LED
import utils.TAG

object Singleton {

    var gpioButton: Gpio
    private var gpioLed: Gpio
    private var ledState = false

    init {
        Log.i(TAG, "Getting gpio instance")
        gpioButton = PeripheralManager.getInstance().openGpio(PIN_BUTTON)
        gpioLed = PeripheralManager.getInstance().openGpio(PIN_LED)
        Log.i(TAG, "Setting pins as input and output")
        gpioButton.setDirection(Gpio.DIRECTION_IN)
        gpioLed.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW)
        Log.i(TAG, "Setting pin activation on rising edge")
        gpioButton.setEdgeTriggerType(Gpio.EDGE_RISING)
        Log.i(TAG,"End init")
    }

    fun changeState() {
        Log.i(TAG, "Changing LED's state")
        ledState = !ledState
        gpioLed.value = ledState
    }
}