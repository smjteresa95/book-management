package com.example.bookmanagement.util.mapper;

import com.example.bookmanagement.domain.entity.LoanRecord;
import com.example.bookmanagement.web.dto.LoanRecordResponseDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-10T20:32:06+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class LoanRecordMapperImpl implements LoanRecordMapper {

    @Override
    public LoanRecordResponseDto toDto(LoanRecord arg0) {
        if ( arg0 == null ) {
            return null;
        }

        LoanRecordResponseDto.LoanRecordResponseDtoBuilder loanRecordResponseDto = LoanRecordResponseDto.builder();

        loanRecordResponseDto.loanId( arg0.getLoanId() );
        loanRecordResponseDto.bookId( arg0.getBookId() );
        loanRecordResponseDto.userId( arg0.getUserId() );
        loanRecordResponseDto.loanDate( arg0.getLoanDate() );
        loanRecordResponseDto.dueDate( arg0.getDueDate() );
        loanRecordResponseDto.returnDate( arg0.getReturnDate() );

        return loanRecordResponseDto.build();
    }

    @Override
    public LoanRecord toEntity(LoanRecordResponseDto arg0) {
        if ( arg0 == null ) {
            return null;
        }

        LoanRecord.LoanRecordBuilder loanRecord = LoanRecord.builder();

        loanRecord.loanId( arg0.getLoanId() );
        loanRecord.bookId( arg0.getBookId() );
        loanRecord.userId( arg0.getUserId() );
        loanRecord.loanDate( arg0.getLoanDate() );
        loanRecord.dueDate( arg0.getDueDate() );
        loanRecord.returnDate( arg0.getReturnDate() );

        return loanRecord.build();
    }

    @Override
    public void updateFromDto(LoanRecordResponseDto arg0, LoanRecordResponseDto arg1) {
        if ( arg0 == null ) {
            return;
        }

        if ( arg0.getLoanId() != null ) {
            arg1.setLoanId( arg0.getLoanId() );
        }
        if ( arg0.getBookId() != null ) {
            arg1.setBookId( arg0.getBookId() );
        }
        if ( arg0.getUserId() != null ) {
            arg1.setUserId( arg0.getUserId() );
        }
        if ( arg0.getLoanDate() != null ) {
            arg1.setLoanDate( arg0.getLoanDate() );
        }
        if ( arg0.getDueDate() != null ) {
            arg1.setDueDate( arg0.getDueDate() );
        }
        if ( arg0.getReturnDate() != null ) {
            arg1.setReturnDate( arg0.getReturnDate() );
        }
    }
}
