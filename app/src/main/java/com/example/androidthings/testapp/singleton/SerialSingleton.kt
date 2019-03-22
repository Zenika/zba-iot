package com.example.androidthings.testapp.singleton

import android.util.Log
import com.google.android.things.pio.PeripheralManager
import com.google.android.things.pio.UartDevice
import utils.TAG
import java.io.IOException

object SerialSingleton {

    private var uart: UartDevice = PeripheralManager.getInstance().openUartDevice("UART0")

    init {
        uart.apply {
            // Configure the UART port
            setBaudrate(115200)
            setDataSize(8)
            setParity(UartDevice.PARITY_NONE)
            setStopBits(1)
            setHardwareFlowControl(UartDevice.HW_FLOW_CONTROL_NONE)
        }
    }

    /** Convert received byte array to String :
     *      @filter : get only vale while incoming byte
     *      @joinToString : add byte to string together with no separator
     *                      convert to character and string
     **/
    private fun ByteArray.toReadableString() = filter{it>0.toByte()}.joinToString(separator = ""){it.toChar().toString()}

    @Throws(IOException::class)
    private fun read(): String {
        val buffer = ByteArray(8)
        var output = ""
        val count = uart.read(buffer, buffer.size)
        output = buffer.toReadableString()
        Log.i(TAG, "Read ${buffer.toReadableString()} $count bytes from peripheral")
        return output
    }

    @Throws(IOException::class)
    private fun write(value: String) {
        val count = uart.write(value.toByteArray(), value.length)
        Log.i(TAG, "Wrote $value $count bytes to peripheral")
    }

    fun getTemp(): String {
        Log.i(TAG,"GetTemp")
        write("T")
        Thread.sleep(500)
        val read = read()
        Log.i(TAG, "Reading : $read")
        return read
    }
}
