package com.baymax104.bookmanager20compose.ui.components

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.baymax104.bookmanager20compose.R
import com.baymax104.bookmanager20compose.ui.components.FloatingMenuScope.FloatingMenuItem
import com.baymax104.bookmanager20compose.ui.components.FloatingMenuState.Values
import com.baymax104.bookmanager20compose.ui.theme.BookManagerTheme

@Composable
fun FloatingMenu(
    size: Dp,
    icon: Int,
    modifier: Modifier = Modifier,
    state: FloatingMenuState = rememberFloatingMenuState(),
    clickClose: Boolean = true,
    contentPadding: Dp = 10.dp,
    content: FloatingMenuScope.() -> Unit
) {
    val scope = FloatingMenuScope().apply(content)
    val transition = updateTransition(targetState = state.currentState, label = "animState")
    val length = scope.items.size
    // 生成透明度动画
    val alphaAnims = buildList {
        repeat(length) { index ->
            val alphaAnim by transition.animateFloat(
                label = "alphaAnim$index",
                transitionSpec = { tween(150) }
            ) {
                when (it) {
                    Values.Open -> 1f
                    Values.Closed -> 0f
                }
            }
            add(alphaAnim)
        }
    }
    // 生成高度动画
    val paddingAnims = buildList {
        repeat(length) { index ->
            val paddingAnim by transition.animateFloat(
                label = "paddingAnim$index",
                transitionSpec = {
                    when (targetState) {
                        Values.Open ->
                            spring(stiffness = Spring.StiffnessMediumLow, dampingRatio = 0.55f)
                        Values.Closed ->
                            spring(stiffness = Spring.StiffnessMediumLow)
                    }
                }
            ) {
                when (it) {
                    Values.Closed -> 0f
                    Values.Open ->
                        (length - index) * (size + contentPadding).value
                }
            }
            add(paddingAnim)
        }
    }
    Box(
        modifier = modifier,
        contentAlignment = Alignment.BottomEnd
    ) {
        scope.items.forEachIndexed { index, item ->
            FloatingMenuButton(
                size = size,
                item = item,
                clickClose = clickClose,
                state = state,
                modifier = Modifier
                    .padding(bottom = paddingAnims[index].dp)
                    .alpha(alphaAnims[index])
            )
        }
        FloatingMainMenuButton(
            state = state,
            size = size,
            icon = icon
        )
    }

}

/**
 * 悬浮菜单子按钮
 *
 * @param size 按钮大小，与主按钮相同
 * @param item [FloatingMenuScope.FloatingMenuItem]实体
 */
@Composable
private fun FloatingMenuButton(
    size: Dp,
    item: FloatingMenuItem,
    clickClose: Boolean,
    state: FloatingMenuState,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End,
        modifier = modifier
    ) {
        Surface(
            content = item.label,
            modifier = Modifier.padding(end = 10.dp),
            color = item.labelBackground(),
            contentColor = item.labelColor()
        )
        FloatingActionButton(
            onClick = {
                item.onClick()
                if (clickClose) {
                    state.close()
                }
            },
            shape = CircleShape,
            contentColor = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(size),
            containerColor = Color.White,
            elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 3.dp)
        ) {
            Icon(
                painter = painterResource(id = item.icon),
                contentDescription = null,
                modifier = Modifier.size(size.times(0.6f))
            )
        }
    }
}

/**
 * 悬浮菜单主按钮
 *
 * @param size 按钮大小
 * @param state 菜单状态[FloatingMenuState]
 * @param icon icon资源id
 */
@Composable
private fun FloatingMainMenuButton(
    size: Dp,
    state: FloatingMenuState,
    icon: Int
) {
    val rotateAnimValue by animateFloatAsState(
        targetValue = if (state.isOpen) 45f else 0f,
        label = "rotate"
    )
    Row(horizontalArrangement = Arrangement.End) {
        FloatingActionButton(
            onClick = { state.inverse() },
            elevation = FloatingActionButtonDefaults.elevation(
                defaultElevation = 3.dp
            ),
            shape = CircleShape,
            contentColor = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(size)
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                modifier = Modifier
                    .size(size.times(0.6f))
                    .graphicsLayer(rotationZ = rotateAnimValue)
            )
        }
    }
}


/**
 * 悬浮按钮菜单状态
 * @constructor 有参构造
 *
 * @param initialValue 初值，默认为[Values.Closed]
 */
@Stable
class FloatingMenuState(
    initialValue: Values = Values.Closed
) {

    /**
     * 菜单状态值：开启、关闭
     */
    enum class Values {
        Open,
        Closed
    }

    var currentState by mutableStateOf(initialValue)

    val isOpen: Boolean
        get() = currentState == Values.Open

    val isClosed: Boolean
        get() = currentState == Values.Closed

    fun open() {
        currentState = Values.Open
    }

    fun close() {
        currentState = Values.Closed
    }

    fun inverse() {
        currentState = when(currentState) {
            Values.Open -> Values.Closed
            Values.Closed -> Values.Open
        }
    }

    companion object {
        val Saver = Saver<FloatingMenuState, Values>(
            save = { it.currentState },
            restore = { FloatingMenuState(it) }
        )
    }

}

/**
 * 悬浮菜单作用域，可添加[FloatingMenuItem]
 *
 * @constructor Create empty Floating menu scope
 */
class FloatingMenuScope {

    /**
     * 菜单项数据实体类
     *
     * @property icon icon资源id
     * @property onClick 点击回调
     * @property label 提示文字Composable
     * @property labelColor 文字颜色
     * @property labelBackground 文字背景
     * @constructor Create empty Floating menu item
     */
    data class FloatingMenuItem(
        var icon: Int,
        val onClick: () -> Unit,
        var label: @Composable () -> Unit = {},
        var labelColor: @Composable () -> Color = { Color.Black },
        var labelBackground: @Composable () -> Color = { Color.Transparent },
    )

    internal val items: MutableList<FloatingMenuItem> = mutableListOf()

    /**
     * Item
     *
     * 添加[FloatingMenuItem]对象
     *
     * @param icon icon资源id
     * @param label label文字Composable
     * @param labelColor 文字颜色
     * @param labelBackground 文字背景
     * @param onClick 点击事件回调
     */
    fun item(
        icon: Int,
        label: @Composable () -> Unit = {},
        labelColor: @Composable () -> Color = { Color.Black },
        labelBackground: @Composable () -> Color = { Color.Transparent },
        onClick: () -> Unit
    ) {
        items += FloatingMenuItem(icon, onClick, label, labelColor, labelBackground)
    }
}

/**
 * Remember floating button menu state
 *
 * @param initialValue 初始值
 * @return 悬浮菜单Remember状态
 */
@Composable
fun rememberFloatingMenuState(
    initialValue: Values = Values.Closed
): FloatingMenuState {
   return rememberSaveable(saver = FloatingMenuState.Saver) {
       FloatingMenuState(initialValue)
   }
}

@Preview(showBackground = true)
@Composable
fun PreviewFloating() {
    BookManagerTheme {
        FloatingMenu(
            size = 50.dp,
            icon = R.drawable.add
        ) {
            item(
                icon = R.drawable.add,
                label = { Text(text = "Hello", style = MaterialTheme.typography.labelSmall) },
            ) {

            }
        }
    }
}