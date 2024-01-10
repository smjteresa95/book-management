package com.example.bookmanagement.util.mapper;

import com.example.bookmanagement.domain.entity.LoanRecord;
import com.example.bookmanagement.web.dto.LoanRecordResponseDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-11T00:23:02+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class LoanRecordResponseMapperImpl implements LoanRecordResponseMapper {

    @Override
    public LoanRecordResponseDto toDto(LoanRecord e) {
        if ( e == null ) {
            return null;
        }

        LoanRecordResponseDto.LoanRecordResponseDtoBuilder loanRecordResponseDto = LoanRecordResponseDto.builder();

        loanRecordResponseDto.loanId( e.getLoanId() );
        loanRecordResponseDto.bookId( e.getBookId() );
        loanRecordResponseDto.userId( e.getUserId() );
        loanRecordResponseDto.loanDate( e.getLoanDate() );
        loanRecordResponseDto.dueDate( e.getDueDate() );
        loanRecordResponseDto.returnDate( e.getReturnDate() );

        return loanRecordResponseDto.build();
    }

    @Override
    public LoanRecord toEntity(LoanRecordResponseDto d) {
        if ( d == null ) {
            return null;
        }

        LoanRecord.LoanRecordBuilder loanRecord = LoanRecord.builder();

        loanRecord.loanId( d.getLoanId() );
        loanRecord.bookId( d.getBookId() );
        loanRecord.userId( d.getUserId() );
        loanRecord.loanDate( d.getLoanDate() );
        loanRecord.dueDate( d.getDueDate() );
        loanRecord.returnDate( d.getReturnDate() );

        return loanRecord.build();
    }

    @Override
    public void updateFromDto(LoanRecordResponseDto dto, LoanRecordResponseDto target) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getLoanId() != null ) {
            target.setLoanId( dto.getLoanId() );
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
