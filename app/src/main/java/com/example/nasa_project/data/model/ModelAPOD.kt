package com.example.nasa_project.data.model

import com.fasterxml.jackson.annotation.JsonProperty


data class ModelAPOD(
    @JsonProperty("copyright")
    var copyright: String?,
    @JsonProperty("date")
    var date: String?,
    @JsonProperty("explanation")
    var explanation: String?,
    @JsonProperty("hdurl")
    var hdurl: String?,
    @JsonProperty("media_type")
    var media_type: String?,
    @JsonProperty("service_version")
    var service_version: String?,
    @JsonProperty("title")
    var title: String?,
    @JsonProperty("url")
    var url: String?
)