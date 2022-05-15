package com.ghg.favmusicapp.data.repository

import com.ghg.favmusicapp.data.api.ItunesApi
import com.ghg.favmusicapp.data.api.mapper.toDomain
import com.ghg.favmusicapp.domain.models.itunes.ResultDetail
import com.ghg.favmusicapp.domain.repository.ItunesRepository

class ItunesRepositoryImpl constructor(
  private val itunesApi: ItunesApi
) : ItunesRepository {

  override suspend fun getSearchResult(
    term: String
  ): List<ResultDetail> = itunesApi.getResults(term).toDomain()
}