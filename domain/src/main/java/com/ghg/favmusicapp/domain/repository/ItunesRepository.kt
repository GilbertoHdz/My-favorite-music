package com.ghg.favmusicapp.domain.repository

import com.ghg.favmusicapp.domain.models.itunes.ResultDetail

interface ItunesRepository {

  suspend fun getSearchResult(term: String): List<ResultDetail>
}