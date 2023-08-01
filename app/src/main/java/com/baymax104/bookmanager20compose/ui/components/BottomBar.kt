package com.baymax104.bookmanager20compose.ui.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.baymax104.bookmanager20compose.ui.navigation.LocalMainNav
import com.baymax104.bookmanager20compose.ui.navigation.Nav
import com.baymax104.bookmanager20compose.ui.navigation.mainNavs
import com.baymax104.bookmanager20compose.ui.theme.BookManagerTheme
import com.baymax104.bookmanager20compose.ui.theme.ContainerColor

/**
 * 底部导航栏
 * @author John
 */

@Composable
fun BottomBar(
    navs: List<Nav>,
    navController: NavController = LocalMainNav.current,
) {
    val entry by navController.currentBackStackEntryAsState()
    NavBar {
        navs.forEach { nav ->
            val current = entry?.destination?.route
            NavItem(
                selected = current == nav.route,
                nav = nav
            ) {
                navController.navigate(nav.route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                    anim {

                    }
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
    nav: Nav,
    onClick: () -> Unit,
) {
    NavigationBarItem(
        selected = selected,
        icon = {
            Icon(
                painter = painterResource(id = nav.icon),
                contentDescription = null,
                modifier = Modifier.size(28.dp)
            )
        },
        onClick = onClick,
        colors = NavigationBarItemDefaults.colors(
            selectedTextColor = MaterialTheme.colorScheme.primary,
            selectedIconColor = MaterialTheme.colorScheme.primary,
            indicatorColor = Color(0xFFD5EBFF)
        ),
        label = {
            Text(
                text = nav.label,
                style = MaterialTheme.typography.labelSmall
            )
        },
        alwaysShowLabel = false,
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
