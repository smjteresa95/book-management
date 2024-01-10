package com.example.bookmanagement.util.mapper;

import com.example.bookmanagement.domain.entity.LoanRecord;
import com.example.bookmanagement.web.dto.BookLoanUpdateDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "Spring")
public interface LoanRecordUpdateMapper extends GenericMapper<BookLoanUpdateDto, LoanRecord>{
}
