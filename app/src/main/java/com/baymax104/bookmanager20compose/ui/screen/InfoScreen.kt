package com.baymax104.bookmanager20compose.ui.screen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.baymax104.bookmanager20compose.R
import com.baymax104.bookmanager20compose.bean.vo.ProgressBookView
import com.baymax104.bookmanager20compose.ui.components.SelectionButton
import com.baymax104.bookmanager20compose.ui.theme.BookManagerTheme
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.result.EmptyResultBackNavigator
import com.ramcosta.composedestinations.result.ResultBackNavigator
import com.ramcosta.composedestinations.spec.DestinationStyleBottomSheet

/**
 * 请求图书信息展示
 * @author John
 * @since 2024/1/27
 */
@Destination(style = DestinationStyleBottomSheet::class)
@Composable
fun InfoScreen(
    book: ProgressBookView,
    navigator: ResultBackNavigator<ProgressBookView>
) {
    InfoContent(
        book = book,
        onCancel = {
            navigator.navigateBack()
        },
        onConfirm = {
            navigator.navigateBack(book)
        }
    )
}

@Composable
private fun InfoContent(
    book: ProgressBookView,
    onConfirm: () -> Unit,
    onCancel: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "图书信息",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(vertical = 10.dp)
        )
        Divider(color = MaterialTheme.colorScheme.primary)
        InfoLabel(
            title = "基本信息",
            modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 10.dp)
        )
        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .height(180.dp)
        ) {
            Card(
                modifier = Modifier
                    .width(140.dp)
                    .height(180.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xffeeeeee))
            ) {
                AsyncImage(
                    model = book.image,
                    contentDescription = null,
                    contentScale = ContentScale.FillHeight,
                    placeholder = painterResource(id = R.drawable.no_image)
                )
            }
            Column(
                modifier = Modifier
                    .padding(start = 10.dp)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = book.title,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                MainInfoText(text = book.author)
                MainInfoText(text = "页数：${book.page}页")
                MainInfoText(text = "出版社：${book.publisher}")
                MainInfoText(text = "ISBN：${book.isbn}")
            }
        }

        InfoLabel(
            title = "图书简介",
            modifier = Modifier.padding(horizontal = 10.dp)
        )
        Text(
            text = book.description,
            style = MaterialTheme.typography.displaySmall,
            modifier = Modifier.padding(10.dp)
        )
        SelectionButton(onConfirm, onCancel)
    }
}

@Composable
private fun MainInfoText(text: String) {
    Text(
        text = text,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        style = MaterialTheme.typography.displayMedium,
        fontSize = 13.sp,
        fontWeight = FontWeight.Normal
    )
}

@Composable
private fun InfoLabel(
    title: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(30.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        VerticalLine(
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .padding(end = 5.dp, top = 5.dp, bottom = 5.dp)
                .width(5.dp)
                .fillMaxHeight()
        )
        Text(
            text = title,
            style = MaterialTheme.typography.displayMedium,
            color = Color(0xff616161),
            fontSize = 14.sp
        )
    }
}

@Composable
private fun VerticalLine(
    color: Color,
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier) {
        val xOffset = size.width / 2
        val yStart = 0f
        val yEnd = size.height
        val start = Offset(xOffset, yStart)
        val end = Offset(xOffset, yEnd)
        drawLine(
            color = color,
            start = start,
            end = end,
            strokeWidth = size.width,
            cap = StrokeCap.Round
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewInfo() {
    BookManagerTheme {
        InfoScreen(
            ProgressBookView(),
            navigator = EmptyResultBackNavigator()
        )
//        InfoLabel(title = "Hello")
    }
}