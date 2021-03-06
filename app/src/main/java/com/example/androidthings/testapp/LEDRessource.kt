package com.example.androidthings.testapp

import android.util.Log
import org.json.JSONObject
import org.restlet.data.MediaType
import org.restlet.ext.json.JsonRepresentation
import org.restlet.representation.Representation
import org.restlet.representation.StringRepresentation
import org.restlet.resource.Post
import org.restlet.resource.ServerResource
import utils.TAG

class LEDResource: ServerResource() {

    @Post("json")
    fun postState(entity: JsonRepresentation): Representation {
        var result = JSONObject()

        Log.i(TAG, "@Post : $result")
        try {
            val json = JsonRepresentation(entity)
            result = json.jsonObject
            val state = result.get("state") as Boolean
            if(state)Singleton.changeState()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return StringRepresentation(result.toString(), MediaType.APPLICATION_ALL_JSON)
    }
}
