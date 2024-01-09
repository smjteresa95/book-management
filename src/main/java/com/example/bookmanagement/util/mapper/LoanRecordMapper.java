package com.example.bookmanagement.util.mapper;

import com.example.bookmanagement.domain.entity.Book;
import com.example.bookmanagement.domain.entity.LoanRecord;
import com.example.bookmanagement.web.dto.LoanRecordResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LoanRecordMapper extends GenericMapper<LoanRecordResponseDto, LoanRecord> {
}
