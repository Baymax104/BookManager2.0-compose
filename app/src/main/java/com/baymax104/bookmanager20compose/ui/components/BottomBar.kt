package com.baymax104.bookmanager20compose.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.baymax104.bookmanager20compose.ui.screen.IndexPage
import com.baymax104.bookmanager20compose.ui.theme.BookManagerTheme
import com.baymax104.bookmanager20compose.ui.theme.ContainerColor
import com.baymax104.bookmanager20compose.ui.theme.IndicatorColor
import kotlinx.coroutines.launch

/**
 * 底部导航栏
 * @author John
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BottomBar(
    indexPages: List<IndexPage>,
    pagerState: PagerState
) {
    val scope = rememberCoroutineScope()
    NavBar {
        indexPages.forEachIndexed { index, item ->
            NavItem(
                selected = pagerState.currentPage == index,
                indexPage = item
            ) {
                if (pagerState.currentPage != index) {
                    scope.launch { pagerState.animateScrollToPage(index) }
                }
            }
        }
    }
}

@Composable
private fun NavBar(content: @Composable RowScope.() -> Unit) {
    NavigationBar(
        contentColor = MaterialTheme.colorScheme.primary,
        containerColor = ContainerColor,
        tonalElevation = 0.dp,
        content = content
    )
}

@Composable
private fun RowScope.NavItem(
    selected: Boolean,
    indexPage: IndexPage,
    onClick: () -> Unit,
) {
    NavigationBarItem(
        selected = selected,
        icon = {
            Icon(
                painter = painterResource(id = indexPage.icon),
                contentDescription = null,
                modifier = Modifier.size(30.dp)
            )
        },
        onClick = onClick,
        colors = NavigationBarItemDefaults.colors(
            selectedTextColor = MaterialTheme.colorScheme.primary,
            selectedIconColor = MaterialTheme.colorScheme.primary,
            indicatorColor = IndicatorColor
        ),
        label = {
            Text(
                text = indexPage.label,
                style = MaterialTheme.typography.labelSmall,
            )
        },
        alwaysShowLabel = false,
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun PreviewNav() {
    val pagerState = rememberPagerState { 2 }
    val pages = listOf(IndexPage.Progress, IndexPage.Finish)
    BookManagerTheme {
        BottomBar(pagerState = pagerState, indexPages = pages)
    }
}
