package com.example.atonce.presentation.home.view.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun AdPager(
    ads: List<Int>,
    modifier: Modifier = Modifier,
    height: Dp = 150.dp,
    autoScrollDelay: Long = 3000,
    contentScale: ContentScale = ContentScale.Crop,
    onPageChanged: (Int) -> Unit = {}
) {
    val pagerState = rememberPagerState(pageCount = { ads.size })

    LaunchedEffect(pagerState) {
        while (true) {
            delay(autoScrollDelay)
            val nextPage = (pagerState.currentPage + 1) % pagerState.pageCount
            pagerState.animateScrollToPage(nextPage)
            onPageChanged(nextPage)
        }
    }

    HorizontalPager(
        state = pagerState,
        modifier = modifier.height(height)
    ) { page ->
        Image(
            painter = painterResource(id = ads[page]),
            contentDescription = "Promo ${page + 1}",
            contentScale = contentScale,
            modifier = Modifier.fillMaxSize()
        )
    }

    DotIndicator(
        totalDots = ads.size,
        selectedIndex = pagerState.currentPage,
        modifier = Modifier.padding(top = 8.dp)
    )
}