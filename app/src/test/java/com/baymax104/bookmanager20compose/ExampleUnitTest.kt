package com.baymax104.bookmanager20compose

import com.baymax104.bookmanager20compose.bean.dto.BookDto
import com.baymax104.bookmanager20compose.bean.dto.Response
import com.baymax104.bookmanager20compose.util.JsonCoder
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun jsonTest() {
        val str = """
            {
              "errcode":0,
              "data":{
                "summary":"　　本书赢得了全球程序员的广泛赞誉，即使是最晦涩的概念，在Bruce Eckel的文字亲和力和小而直接的编程示例面前也会化解于无形。从Java的基础语法到最高级特性（深入的面向对象概念、多线程、自动项目构建、单元测试和调试等），本书都能逐步指导你轻松掌握。从本书获得的各项大奖以及来自世界各地的读者评论中，不难看出这是一本经典之作。本书的作者拥有多年教学经验，对C、C++以及Java语言都有独到、深入的见解，以通俗易懂及小而直接的示例解释了一个个晦涩抽象的概念。本书共22章，包括操作符、控制执行流程、访问权限控制、复用类、多态、接口、通过异常处理错误、字符串、泛型、数组、容器深入研究、Java I/O系统、枚举类型、并发以及图形化用户界面等内容。这些丰富的内容，包含了Java语言基础语法以及高级特性，适合各个层次的Java程序员阅读，同时也是高等院校讲授面向对象程序设计语言以及Java语言的绝佳教材和参考书。 第4版特点：适合初学者与专业人员的经典的面向对象叙述方式，为更新的Java SE5/6增加了新的示例和章节。测验框架显示程序输出。设计模式贯穿于众多示例中：适配器、桥接器、职责链、命令、装饰器、外观、工厂方法、享元、点名、数据传输对象、空对象、代理、单例、状态、策略、模板方法以及访问者。为数据传输引入了XML，为用户界面引入了SWT和Flash。重新撰写了有关并发的章节，有助于读者掌握线程的相关知识。专门为第4版以及Java SE5/6重写了700多个编译文件中的500多个程序。支持网站包含了所有源代码、带注解的解决方案指南、网络日志以及多媒体学习资料。覆盖了所有基础知识，同时论述了高级特性。详细地阐述了面向对象原理。在线可获得Java讲座CD，其中包含Bruce Eckel的全部多媒体讲座。在www.MindView.net网站上可以观看现场讲座、咨询和评论。专门为第4版以及Java SE5/6重写了700多个编译文件中的500多个程序。支持网站包含了所有源代码、带注解的解决方案指南、网络日志以及多媒体学习资料。覆盖了所有基础知识，同时论述了高级特性。详细地阐述了面向对象原理。在线可获得Java讲座CD，其中包含Bruce Eckel的全部多媒体讲座。在www.MindView.net网站上可以观看现场讲座、咨询和评论。",
                "img":"https://img1.ibook.tech/2020/11/16/205cb300770a.jpg?x-oss-process=image/auto-orient,1/resize,p_50/quality,q_50/format,jpg",
                "author":"（美）埃克尔 著",
                "translator":"陈昊鹏 译",
                "isbn":"9787111213826",
                "chinaclass":"TP312",
                "binding":"胶版纸",
                "language":"中文",
                "title":"Java编程思想（第4版）",
                "publisherAddress":"北京",
                "price":"108.00元",
                "publisher":"机械工业出版社",
                "isbn10":"7111213823",
                "page":"880页",
                "category":"工业技术",
                "pubdate":"2007-06-01"
              },
              "errmsg":"成功"
            }
        """.trimIndent()
        val response = JsonCoder.decodeFromString<Response<BookDto>>(str)
        println(response)
    }
}