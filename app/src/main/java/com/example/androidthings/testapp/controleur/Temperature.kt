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

    /** if Get :
     * @param json object
     * call getTemp function
     * @return json with temperature
     **/
    @Get("json")
    fun getState(): Representation {
        val result = JSONObject()
        val temp = SerialSingleton.getTemp()
        Log.i(TAG, "@Get : $temp")
        result.put("temp", temp)
        return StringRepresentation(result.toString(), MediaType.APPLICATION_ALL_JSON)
    }
}