package com.xf.oschina.api;

import android.arch.lifecycle.LiveData;

import com.xf.oschina.module.story.domain.BookChapter;
import com.xf.oschina.module.story.domain.ChapterRead;
import com.xf.oschina.module.story.domain.EBookComment;
import com.xf.oschina.module.story.domain.EBookData;
import com.xf.oschina.module.story.domain.EBookDetail;
import com.xf.oschina.module.story.domain.EBook;
import com.xf.oschina.utils.ApiResponse;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface EbookApiService {
    /**
     * 获取单一排行榜
     * 周榜：rankingId->_id
     * 月榜：rankingId->monthRank
     * 总榜：rankingId->totalRank
     *
     * @return
     */
    @GET("/ranking/{rankingId}")
    LiveData<ApiResponse<EBook>> getEbook(@Path("rankingId") String rankingId);

    /**
     * 图书详情
     *
     * @param bookId
     * @return
     */
    @GET("/book/{bookId}")
    LiveData<ApiResponse<EBookDetail>> getBookDetail(@Path("bookId") String bookId);

    /**
     * 热门评论 图书详情页
     *
     * @param book
     * @param limit
     * @return
     */
    @GET("/post/review/best-by-book")
    LiveData<ApiResponse<EBookData>> getEBookComment(@Query("book") String book, @Query("limit") int limit);

    /**
     * 章节目录
     *
     * @param bookId
     * @return
     */
    @GET("/mix-toc/{bookId}")
    LiveData<ApiResponse<BookChapter>> getBookChapters(@Path("bookId") String bookId);

    /***
     * 获取图书阅读内容
     *
     * @param url
     * @return
     */
    @GET("http://chapter2.zhuishushenqi.com/chapter/{url}")
    LiveData<ApiResponse<ChapterRead>> getChapterContent(@Path("url") String url);
}
