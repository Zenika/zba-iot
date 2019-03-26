package com.example.androidthings.testapp.controleur

import android.util.Log
import com.example.androidthings.testapp.singleton.SerialSingleton
import org.json.JSONObject
import org.restlet.data.MediaType
import org.restlet.ext.json.JsonRepresentation
import org.restlet.representation.Representation
import org.restlet.representation.StringRepresentation
import org.restlet.resource.Get
import org.restlet.resource.Post
import org.restlet.resource.ServerResource
import utils.TAG

/** Controleur to get temperature from android things Device**/
class Temperature: ServerResource() {

    /** if post :
     * @param json object
     * call getTemp function
     * @return json with temperature
     **/
    @Get("")
    fun getState(entity: JsonRepresentation): Representation {
        var result = JSONObject()
        var get = "404 error"

        Log.i(TAG, "@Get : $result")
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