package com.samridhi.blogingapp.data.network.repositories

import com.samridhi.blogingapp.data.BlogApiClientService
import javax.inject.Inject

class AppRepositories @Inject constructor(
    private val blogApiClientService: BlogApiClientService
) {
    suspend fun getPosts(perPage: Int, page: Int) =
        blogApiClientService.searchBlog(perPage = perPage, page = page)
}
