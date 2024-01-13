package com.iiht.training.loan.service;


import com.iiht.training.loan.entity.Loan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LoanService {
    Page<Loan> getAllLoan(Pageable pageable);
    Loan saveLoan(Loan loan);

    Loan getLoanById(Long id);

    Loan updateLoan(Loan loan);

    boolean deleteLoanById(Long id);
    Page<Loan> getByKeyword(String keyword, Pageable pageable);

    boolean updateLoanStatus(boolean active, Long id);
}
