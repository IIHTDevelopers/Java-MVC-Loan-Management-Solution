package com.iiht.training.loan.service.impl;

import com.iiht.training.loan.entity.Loan;
import com.iiht.training.loan.repository.LoanRepository;
import com.iiht.training.loan.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanServiceImpl implements LoanService {
   @Autowired
   private LoanRepository loanRepository;

    @Override
    public List<Loan> getAllLoan() {
        return loanRepository.findAll();
    }

    @Override
    public Loan saveLoan(Loan loan) {
        return loanRepository.save(loan);
    }

    @Override
    public Loan getLoanById(Long id) {
        return loanRepository.findById(id).get();
    }

    @Override
    public Loan updateLoan(Loan loan) {
        return loanRepository.save(loan);
    }

    @Override
    public boolean deleteLoanById(Long id) {
        loanRepository.deleteById(id);
        return true;
    }

    public List<Loan> getByKeyword(String keyword){
        return loanRepository.findByKeyword(keyword);
    }

}
