package com.example.deptapp.data

import org.json.JSONArray
import java.io.Serializable

data class EventData(
    val eventID: String,
    val eventTitle: String,
    val eventImageUrl: JSONArray,
    val eventTime: String,
    val eventDesc: String
): Serializable