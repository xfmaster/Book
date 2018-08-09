package com.xf.oschina.api;

import android.arch.lifecycle.LiveData;

import com.xf.oschina.module.book.domain.BookData;
import com.xf.oschina.module.book.domain.CommentData;
import com.xf.oschina.module.login.domain.Token;
import com.xf.oschina.module.user.domain.User;
import com.xf.oschina.utils.ApiResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ObjectApiService {
    @GET("/action/oauth2/authorize?response_type=code&client_id=BbYL7V4Z7eq9vV0e0I1P&state=xyz&redirect_uri=https://www.oschina.net/")
    LiveData<ApiResponse<Token>> getToken();

    @GET("users/{login}")
    LiveData<ApiResponse<User>> getUser(@Path("login") String login);

    /**
     * @author xf
     * @time 2018/7/5  15:33
     * @describe 获取图书列表
     */
    @GET("v2/book/search")
    LiveData<ApiResponse<BookData>> getBooks(@QueryMap Map<String, String> params);

    //@Path("bookId") String bookId, @Query("start") int start, @Query("count") int count, @Query("fields") String fields
    @GET("v2/book/{bookId}/reviews")
    LiveData<ApiResponse<CommentData>> getBookReviews(@Path("bookId") String bookId, @QueryMap Map<String, String> params);

//    @GET("book/series/{seriesId}/books")
//    Observable<Response<BookSeriesListResponse>> getBookSeries(@Path("seriesId") String seriesId, @Query("start") int start, @Query("count") int count, @Query("fields") String fields);
}
