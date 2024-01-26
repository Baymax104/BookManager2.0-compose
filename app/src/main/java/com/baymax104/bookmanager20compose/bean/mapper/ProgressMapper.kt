package com.baymax104.bookmanager20compose.bean.mapper

import com.baymax104.bookmanager20compose.bean.dto.BookDto
import com.baymax104.bookmanager20compose.bean.entity.BookEntity
import com.baymax104.bookmanager20compose.bean.vo.ProgressBookView
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings
import org.mapstruct.ReportingPolicy
import org.mapstruct.factory.Mappers

/**
 * Progress对象转换
 * @author John
 * @since 2024/1/26
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface IProgressMapper {

    @Mappings(
        Mapping(source = "summary", target = "description"),
        Mapping(source = "img", target = "image"),
        Mapping(target = "title", ignore = true),
        Mapping(target = "author", ignore = true),
    )
    fun dto2View(dto: BookDto): ProgressBookView

    fun entity2View(entity: BookEntity): ProgressBookView

    fun view2Entity(view: ProgressBookView): BookEntity
}

val ProgressMapper: IProgressMapper = Mappers.getMapper(IProgressMapper::class.java)