package com.iiht.training.loan.service;


import com.iiht.training.loan.entity.Loan;

import java.util.List;

public interface LoanService {
    List<Loan> getAllLoan();
    Loan saveLoan(Loan loan);

    Loan getLoanById(Long id);

    Loan updateLoan(Loan loan);

    boolean deleteLoanById(Long id);
    public List<Loan> getByKeyword(String keyword);
}
