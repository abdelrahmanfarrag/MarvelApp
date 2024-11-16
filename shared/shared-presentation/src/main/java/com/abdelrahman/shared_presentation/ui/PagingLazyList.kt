package com.abdelrahman.shared_presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import com.abdelrahman.shared_presentation.models.PagingComponentModel
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> PagingLazyList(
    modifier: Modifier = Modifier,
    pagingComponentModel: PagingComponentModel<T>,
    extraContent: (@Composable () -> Unit) = {},
    singleItemContent: (@Composable (T) -> Unit) = {}
) {
    val lazyListState = rememberLazyListState()
    val loadingType = pagingComponentModel.loadingTypes
    val shouldLoadMore = remember {
        derivedStateOf {
            val totalItemsCount = lazyListState.layoutInfo.totalItemsCount
            val lastVisibleItemIndex =
                lazyListState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
                    ?: 0
            lastVisibleItemIndex >= (totalItemsCount - 2)
        }
    }
    LaunchedEffect(lazyListState, loadingType) {
        snapshotFlow { shouldLoadMore.value }.distinctUntilChanged()
            .filter { it }.collect {
                if (loadingType == LoadingTypes.NONE) {
                    pagingComponentModel.iPagingComponentInteractions.onRequestNextPage()
                }
            }
    }
    PullToRefreshBox(
        isRefreshing = loadingType == LoadingTypes.PULL_TO_REFRESH,
        onRefresh = {
            pagingComponentModel.iPagingComponentInteractions.onPullToRefresh()
        }) {
        Column(modifier = modifier) {
            extraContent()
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                state = lazyListState
            ) {
                items(pagingComponentModel.listItems ?: arrayListOf()) {
                    singleItemContent(it)
                }
                pagingProgressbar(
                    loadingType == LoadingTypes.PAGING_PROGRESS
                )
            }
        }

    }

}