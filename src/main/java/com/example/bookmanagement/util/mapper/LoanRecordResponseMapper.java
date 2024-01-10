package com.example.bookmanagement.util.mapper;

import com.example.bookmanagement.domain.entity.LoanRecord;
import com.example.bookmanagement.web.dto.LoanRecordResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "Spring")
public interface LoanRecordResponseMapper extends GenericMapper<LoanRecordResponseDto, LoanRecord> {
}
