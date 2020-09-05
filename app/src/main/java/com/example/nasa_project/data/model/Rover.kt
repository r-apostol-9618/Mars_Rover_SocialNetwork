package com.example.nasa_project.data.model

import com.fasterxml.jackson.annotation.JsonProperty

data class Rover(
    @JsonProperty("id")
    var id: Int?,
    @JsonProperty("landing_date")
    var landing_date: String?,
    @JsonProperty("launch_date")
    var launch_date: String?,
    @JsonProperty("name")
    var name: String?,
    @JsonProperty("status")
    var status: String?
)