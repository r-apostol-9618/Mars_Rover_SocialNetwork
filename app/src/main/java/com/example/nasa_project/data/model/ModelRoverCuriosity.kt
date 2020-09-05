package com.example.nasa_project.data.model

import com.fasterxml.jackson.annotation.JsonProperty

data class ModelRoverCuriosity(
    @JsonProperty("photos")
     var photos: List<Photo>?
)
