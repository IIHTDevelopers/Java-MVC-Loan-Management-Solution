package com.iiht.training.loan.controller;

import com.iiht.training.loan.entity.Loan;
import com.iiht.training.loan.service.LoanService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class LoanController {
    private LoanService loanService;


    public LoanController(LoanService loanService) {
        super();
        this.loanService = loanService;
    }

    @RequestMapping(path = {"/", "/search"})
    public String home(Loan loan, Model model, String keyword) {
        if (keyword != null) {
            List<Loan> list = loanService.getByKeyword(keyword);
            model.addAttribute("list", list);
        } else {
            List<Loan> list = loanService.getAllLoan();
            model.addAttribute("list", list);
        }
        return "loan_list";
    }

    @GetMapping("/loan")
    public String listLoan(Model model) {
        model.addAttribute("loans", loanService.getAllLoan());
        return "loan";
    }

    @GetMapping("/loan/new")
    public String createLoanForm(Model model) {


        Loan loan = new Loan();
        model.addAttribute("loan", loan);
        return "create_loan";

    }

    @PostMapping("/loan")
    public String saveLoan(@Valid @ModelAttribute("loan") Loan loan) {
        loanService.saveLoan(loan);
        return "redirect:/loan";
    }

    @GetMapping("/loan/edit/{id}")
    public String editLoanForm(@PathVariable Long id, Model model) {
        model.addAttribute("loan", loanService.getLoanById(id));
        return "edit_loan";
    }

    @PostMapping("/loan/{id}")
    public String updateLoan(@PathVariable Long id,
                             @Valid @ModelAttribute("loan") Loan loan,
                             Model model) {


        Loan existingLoan = loanService.getLoanById(id);
        existingLoan.setId(id);
        existingLoan.setFirstName(loan.getFirstName());
        existingLoan.setLastName(loan.getLastName());
        existingLoan.setEmail(loan.getEmail());
        existingLoan.setAmount(loan.getAmount());
        existingLoan.setInterest(loan.getInterest());
        existingLoan.setTerm(loan.getTerm());


        loanService.updateLoan(existingLoan);
        return "redirect:/loan";
    }


    @GetMapping("/loan/{id}")
    public String deleteLoan(@PathVariable Long id) {
        loanService.deleteLoanById(id);
        return "redirect:/loan";
    }
}
