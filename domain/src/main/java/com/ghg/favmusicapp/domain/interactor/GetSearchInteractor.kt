package com.ghg.favmusicapp.domain.interactor

import com.ghg.favmusicapp.domain.repository.ItunesRepository
import javax.inject.Inject

class GetSearchInteractor @Inject constructor(
  private val itunesRepository: ItunesRepository
) {
  fun execute(query: String) = itunesRepository.getSearchResult(query)
}