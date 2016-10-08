package com.oakkub.gankapitest.data.gank

import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by OaKKuB on 8/21/2016.
 */
@Singleton
class GankRepository @Inject constructor(private val apiService: GankApiService) {

    fun getPhotos(page: Int = 1,
                  limit: Int = 50,
                  keyword: String = "福利") = apiService.getGankPhotos(keyword, limit, page).map { it.results }

}
