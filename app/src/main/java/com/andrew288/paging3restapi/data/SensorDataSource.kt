package com.andrew288.paging3restapi.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.andrew288.paging3restapi.data.remote.SensorApi
import com.andrew288.paging3restapi.data.remote.SensorDto
import com.andrew288.paging3restapi.domain.Sensor

class SensorDataSource(
    private val repo: SensorApi
): PagingSource<Int, SensorDto>() {
    override fun getRefreshKey(state: PagingState<Int, SensorDto>): Int? {
        return state.anchorPosition?.let {
            position ->
            val page = state.closestPageToPosition(position)
            page?.prevKey?.minus(1) ?: page?.nextKey?.plus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SensorDto> {
        return try {
            val page = params.key ?: 1
            val response = repo.getSensorData(page, params.loadSize)
            LoadResult.Page(
                data = response,
                prevKey = null,
                nextKey = if (response.isNotEmpty()) page + 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}