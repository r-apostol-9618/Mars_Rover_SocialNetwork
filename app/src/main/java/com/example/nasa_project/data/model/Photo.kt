package com.example.nasa_project.data.model

import com.fasterxml.jackson.annotation.JsonProperty

data class Photo(
    @JsonProperty("camera")
    var camera: Camera?,
    @JsonProperty("earth_date")
    var earth_date: String?,
    @JsonProperty("id")
    var id: Int?,
    @JsonProperty("img_src")
    var img_src: String?,
    @JsonProperty("rover")
    var rover: Rover?,
    @JsonProperty("sol")
    var sol: Int?
)