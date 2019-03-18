package com.example.androidthings.testapp

import android.app.IntentService
import android.content.Context
import android.content.Intent
import android.util.Log
import org.restlet.Component
import org.restlet.data.Protocol
import org.restlet.engine.Engine
import org.restlet.ext.nio.HttpServerHelper
import org.restlet.routing.Router
import utils.ACTION_START
import utils.ACTION_STOP
import utils.PORT
import utils.TAG

// TODO: AndroidRuntime: java.net.SocketException: Permission denied

open class RESTfulService: IntentService("RestServer") {


    private var component = Component()

    init {
        Log.i(TAG, "clearing Server")
        // "Engine" : instance supporting the Restlet API
        // ".getInstance" : returns the registered Restlet engine
        // ".registeredServers" Returns the list of available server connectors
        // ".clear" : clears the current Restlet Engine
        Engine.getInstance().registeredServers.clear()
        // "HttpServerHelper" : create server connector helper set to null
        val httpServerHelper = HttpServerHelper(null)
        Log.i(TAG, "adding Server")
        // "add", add the connector to the server list
        Engine.getInstance().registeredServers.add(httpServerHelper)
        Log.i(TAG, "Launching HTTP on $PORT")
        // Add the info HTTP server connector to the Restlet component
        component.servers.add(Protocol.HTTP, PORT)
        // create a router
        val router = Router(component.context.createChildContext())
        // Attach the router to the LEDRessource class
        router.attach("/led",LEDResource::class.java)
        // Then attach the server connector to the local host
        component.defaultHost.attach("/rest",router)
        Log.i(TAG,"End init")
    }

    fun serverStatus(context: Context, status: Boolean) {
        val intent = Intent(context,RESTfulService::class.java)
        Log.i(TAG,"test")
        if(status) intent.action = ACTION_START  else intent.action = ACTION_STOP
        Log.i(TAG,"start intent in context")
        context.startService(intent)
        Log.i(TAG,"Service started")
    }

    override fun onHandleIntent(intent: Intent?) {
        Log.i(TAG,"Handling intent status")
        intent?.let {
            if(ACTION_START == intent.action){
                component.start()
                Log.i(TAG, "component.start")
            }else {
                component.stop()
                Log.i(TAG, "component.stop")
            }
        }
    }
}
