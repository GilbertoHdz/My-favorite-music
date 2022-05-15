package com.ghg.favmusicapp.data.repository

import com.ghg.favmusicapp.data.api.ItunesApi
import com.ghg.favmusicapp.data.api.mapper.toDomain
import com.ghg.favmusicapp.data.common.callApi
import com.ghg.favmusicapp.domain.repository.ItunesRepository

class ItunesRepositoryImpl constructor(
  private val itunesApi: ItunesApi
) : ItunesRepository {

  override fun getSearchResult(
    term: String
  ) = callApi { itunesApi.getResults(term).toDomain() }
}