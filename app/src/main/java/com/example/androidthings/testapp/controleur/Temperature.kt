package com.example.androidthings.testapp.controleur

import android.util.Log
import com.example.androidthings.testapp.Singleton.SerialSingleton
import org.json.JSONObject
import org.restlet.data.MediaType
import org.restlet.ext.json.JsonRepresentation
import org.restlet.representation.Representation
import org.restlet.representation.StringRepresentation
import org.restlet.resource.Post
import org.restlet.resource.ServerResource
import utils.TAG

class Temperature: ServerResource() {

    @Post("json")
    fun postState(entity: JsonRepresentation): Representation {
        var result = JSONObject()
        var get = "404 error"

        Log.i(TAG, "@Post : $result")
        try {
            val json = JsonRepresentation(entity)
            result = json.jsonObject
            val temp = result.get("temp") as Boolean
            if(temp) { get = SerialSingleton.getTemp() }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return StringRepresentation(get, MediaType.APPLICATION_ALL_JSON)
    }
}