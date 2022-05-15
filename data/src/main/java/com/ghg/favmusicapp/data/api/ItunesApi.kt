package com.ghg.favmusicapp.data.api

import com.ghg.favmusicapp.data.api.models.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ItunesApi {

  @GET("search")
  suspend fun getResults(
    @Query("term") searchTerm: String
  ): SearchResponse
}