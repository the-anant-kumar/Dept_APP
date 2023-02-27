package com.example.deptapp.data

import org.json.JSONArray

data class EventData(
    val eventID: String,
    val eventTitle: String,
    val eventImageUrl: JSONArray,
    val eventTime: String,
)