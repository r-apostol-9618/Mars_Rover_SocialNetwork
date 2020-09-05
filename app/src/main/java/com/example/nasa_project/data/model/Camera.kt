package com.example.nasa_project.data.model

import com.fasterxml.jackson.annotation.JsonProperty

data class Camera(
    @JsonProperty("full_name")
    var full_name: String?,
    @JsonProperty("id")
    var id: Int?,
    @JsonProperty("name")
    var name: String?,
    @JsonProperty("rover_id")
    var rover_id: Int?
)