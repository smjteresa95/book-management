package com.example.bookmanagement.util.mapper;

import com.example.bookmanagement.domain.entity.LoanRecord;
import com.example.bookmanagement.web.dto.LoanRecordUpdateDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-11T17:25:39+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class LoanRecordUpdateMapperImpl implements LoanRecordUpdateMapper {

    @Override
    public LoanRecordUpdateDto toDto(LoanRecord e) {
        if ( e == null ) {
            return null;
        }

        LoanRecordUpdateDto.LoanRecordUpdateDtoBuilder loanRecordUpdateDto = LoanRecordUpdateDto.builder();

        loanRecordUpdateDto.bookId( e.getBookId() );
        loanRecordUpdateDto.userId( e.getUserId() );
        loanRecordUpdateDto.loanDate( e.getLoanDate() );
        loanRecordUpdateDto.dueDate( e.getDueDate() );
        loanRecordUpdateDto.returnDate( e.getReturnDate() );

        return loanRecordUpdateDto.build();
    }

    @Override
    public LoanRecord toEntity(LoanRecordUpdateDto d) {
        if ( d == null ) {
            return null;
        }

        LoanRecord.LoanRecordBuilder loanRecord = LoanRecord.builder();

        loanRecord.bookId( d.getBookId() );
        loanRecord.userId( d.getUserId() );
        loanRecord.loanDate( d.getLoanDate() );
        loanRecord.dueDate( d.getDueDate() );
        loanRecord.returnDate( d.getReturnDate() );

        return loanRecord.build();
    }

    @Override
    public void updateFromDto(LoanRecordUpdateDto dto, LoanRecordUpdateDto target) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getBookId() != null ) {
            target.setBookId( dto.getBookId() );
        }
        if ( dto.getUserId() != null ) {
            target.setUserId( dto.getUserId() );
        }
        if ( dto.getLoanDate() != null ) {
            target.setLoanDate( dto.getLoanDate() );
        }
        if ( dto.getDueDate() != null ) {
            target.setDueDate( dto.getDueDate() );
        }
        if ( dto.getReturnDate() != null ) {
            target.setReturnDate( dto.getReturnDate() );
        }
    }
}
