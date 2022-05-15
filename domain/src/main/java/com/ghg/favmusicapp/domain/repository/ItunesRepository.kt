package com.ghg.favmusicapp.domain.repository

import com.ghg.favmusicapp.domain.models.itunes.ResultDetail
import kotlinx.coroutines.flow.Flow

interface ItunesRepository {

  fun getSearchResult(term: String): Flow<List<ResultDetail>>
}