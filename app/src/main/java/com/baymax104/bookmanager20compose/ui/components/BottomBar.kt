package com.baymax104.bookmanager20compose.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.baymax104.bookmanager20compose.ui.navigation.Nav
import com.baymax104.bookmanager20compose.ui.navigation.mainNavs
import com.baymax104.bookmanager20compose.ui.theme.BookManagerFont
import com.baymax104.bookmanager20compose.ui.theme.BookManagerTheme
import com.baymax104.bookmanager20compose.ui.theme.ContainerColor
import com.baymax104.bookmanager20compose.ui.theme.MainColor

/**
 * 底部导航栏
 * @author John
 */

@Composable
fun BottomBar(
    navController: NavController,
    navs: List<Nav>,
) {
    val entry by navController.currentBackStackEntryAsState()
    NavBar {
        navs.forEach { nav ->
            val current = entry?.destination?.route
            NavItem(
                selected = current == nav.route,
                nav = nav
            ) {
                navController.navigate(nav.route)
            }
        }
    }
}

@Composable
fun NavBar(content: @Composable RowScope.() -> Unit) {
    NavigationBar(
        containerColor = Color.Transparent,
        contentColor = MaterialTheme.colorScheme.primary,
        content = content,
        modifier = Modifier.background(MaterialTheme.colorScheme.primaryContainer)
    )
}

@Composable
fun RowScope.NavItem(
    selected: Boolean,
    nav: Nav,
    onClick: () -> Unit,
) {
    NavigationBarItem(
        selected = selected,
        icon = {
            Icon(
                painter = painterResource(id = nav.icon),
                contentDescription = null,
                modifier = Modifier.size(30.dp)
            )
        },
        onClick = onClick,
        colors = NavigationBarItemDefaults.colors(
            selectedTextColor = MaterialTheme.colorScheme.primary,
            selectedIconColor = MaterialTheme.colorScheme.primary
        ),
        label = {
            Text(
                text = nav.label,
                style = MaterialTheme.typography.labelSmall
            )
        },
        alwaysShowLabel = false
    )
}

@Preview
@Composable
fun PreviewNav() {
    val navController = rememberNavController()
    BookManagerTheme {
        BottomBar(navController = navController, navs = mainNavs)
    }
}
