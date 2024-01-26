package com.baymax104.bookmanager20compose.ui.components

import androidx.annotation.FloatRange
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.baymax104.bookmanager20compose.bean.vo.ProgressBookView
import com.baymax104.bookmanager20compose.ui.theme.BookManagerFont
import com.baymax104.bookmanager20compose.ui.theme.BookManagerTheme
import com.baymax104.bookmanager20compose.ui.theme.MainColor
import com.baymax104.bookmanager20compose.ui.theme.MainColorShallow
import com.baymax104.bookmanager20compose.util.toDateString

/**
 * 主页列表项
 * @param bookView Book实例
 */
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun LazyItemScope.ProgressItem(
    bookView: ProgressBookView,
    onClick: () -> Unit = {}
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .animateItemPlacement(),
        shape = RoundedCornerShape(20.dp),
        color = Color.White,
        shadowElevation = 5.dp,
        onClick = onClick,
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 7.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = bookView.title,
                modifier = Modifier.weight(1f),
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelMedium
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Text(
                    text = bookView.author,
                    fontFamily = BookManagerFont,
                    fontWeight = FontWeight.Normal,
                    fontSize = 13.sp,
                    color = Color.Gray
                )
                Text(
                    text = bookView.startTime.toDateString(),
                    fontFamily = BookManagerFont,
                    fontWeight = FontWeight.Normal,
                    fontSize = 13.sp,
                    color = Color.Gray
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ProgressBar(
                    progress = bookView.progress,
                    total = bookView.page,
                    modifier = Modifier
                        .weight(1f)
                        .height(12.dp)
                        .clip(RoundedCornerShape(100))
                        .padding(end = 15.dp)
                )
                val ratio = with(bookView) { (progress * 1.0 / page * 100).toInt() }
                Text(
                    text = "$ratio%",
                    textAlign = TextAlign.Center,
                    fontFamily = BookManagerFont,
                    fontWeight = FontWeight.Normal,
                    fontSize = 13.sp,
                    color = Color.Gray
                )
            }
        }
    }
}

@Composable
private fun ProgressBar(
    progress: Int,
    total: Int,
    modifier: Modifier = Modifier
) {
    Canvas(
        modifier = modifier
    ) {
        val strokeWidth = size.height
        val ratio = (progress * 1.0 / total).toFloat()
        drawIndicator(0f, 1f, MainColorShallow, strokeWidth)
        drawIndicator(0f, ratio, MainColor, strokeWidth)
    }
}

private fun DrawScope.drawIndicator(
    @FloatRange(from = 0.0, to = 1.0)
    start: Float,
    @FloatRange(from = 0.0, to = 1.0)
    end: Float,
    color: Color,
    strokeWidth: Float
) {
    val width = size.width
    val height = size.height
    val yOffset = height / 2

    val barStart = width * start
    val barEnd = width * end

    drawLine(
        color = color,
        start = Offset(barStart, yOffset),
        end = Offset(barEnd, yOffset),
        strokeWidth = strokeWidth,
        cap = StrokeCap.Round
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewProgressItem() {
    BookManagerTheme {
        LazyColumn {
            item { ProgressItem(ProgressBookView()) }
        }
    }
}