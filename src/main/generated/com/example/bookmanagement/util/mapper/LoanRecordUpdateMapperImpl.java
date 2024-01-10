package com.example.bookmanagement.util.mapper;

import com.example.bookmanagement.domain.entity.LoanRecord;
import com.example.bookmanagement.web.dto.BookLoanUpdateDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-11T00:23:02+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class LoanRecordUpdateMapperImpl implements LoanRecordUpdateMapper {

    @Override
    public BookLoanUpdateDto toDto(LoanRecord e) {
        if ( e == null ) {
            return null;
        }

        BookLoanUpdateDto.BookLoanUpdateDtoBuilder bookLoanUpdateDto = BookLoanUpdateDto.builder();

        bookLoanUpdateDto.bookId( e.getBookId() );
        bookLoanUpdateDto.userId( e.getUserId() );
        bookLoanUpdateDto.loanDate( e.getLoanDate() );
        bookLoanUpdateDto.dueDate( e.getDueDate() );
        bookLoanUpdateDto.returnDate( e.getReturnDate() );

        return bookLoanUpdateDto.build();
    }

    @Override
    public LoanRecord toEntity(BookLoanUpdateDto d) {
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
    public void updateFromDto(BookLoanUpdateDto dto, BookLoanUpdateDto target) {
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
