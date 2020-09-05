package com.example.nasa_project.data.api

import android.content.Context
import android.content.res.Resources
import com.example.nasa_project.R
import com.example.nasa_project.data.model.ModelAPOD
import com.example.nasa_project.data.model.ModelRoverCuriosity
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


private const val BASE_URL = "https://api.nasa.gov/"

val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(JacksonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

interface ApiServiceCuriosity {
    //@GET("mars-photos/api/v1/rovers/curiosity/photos?sol=1000&api_key=" + ApiKey)
    @GET("mars-photos/api/v1/rovers/curiosity/photos?")
     fun getPhotosCuriosity(
        @Query("api_key") key: String,
        @Query("earth_date") date:String
    ): Call<ModelRoverCuriosity>
}
interface ApiServiceOpportunity {
    //@GET("mars-photos/api/v1/rovers/curiosity/photos?sol=1000&api_key=" + ApiKey)
    @GET("mars-photos/api/v1/rovers/opportunity/photos?")
     fun getPhotosOpportunity(
        @Query("api_key") key: String,
        @Query("earth_date") date:String): Call<ModelRoverCuriosity>
}
interface ApiServiceSpirit{
    //@GET("mars-photos/api/v1/rovers/curiosity/photos?sol=1000&api_key=" + ApiKey)
    @GET("mars-photos/api/v1/rovers/curiosity/photos?")
     fun getPhotosSpirit(
        @Query("api_key") key: String,
        @Query("earth_date") date:String): Call<ModelRoverCuriosity>
}

object ApiServiceCuriosityObject {
    val RETROFIT_SERVICE : ApiServiceCuriosity by lazy {
        retrofit.create(ApiServiceCuriosity::class.java)
    }
}
object ApiServiceOpportunityObject {
    val RETROFIT_SERVICE : ApiServiceOpportunity by lazy {
        retrofit.create(ApiServiceOpportunity::class.java)
    }
}
object ApiServiceSpiritObject {
    val RETROFIT_SERVICE : ApiServiceSpirit by lazy {
        retrofit.create(ApiServiceSpirit::class.java)
    }
}
