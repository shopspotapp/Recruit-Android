package com.oakkub.gankapitest.data.gank

import com.oakkub.gankapitest.data.gank.model.PhotoResult
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by OaKKuB on 8/19/2016.
 */
interface GankApiService {

    @GET("{keyword}/{limit}/{page}")
    fun getGankPhotos(@Path("keyword") keyword: String,
                      @Path("limit") limit: Int,
                      @Path("page") page: Int) : Observable<PhotoResult>

}