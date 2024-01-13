package com.iiht.training.loan.controller;

import com.iiht.training.loan.entity.Loan;
import com.iiht.training.loan.service.LoanService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = {"/loan", "/"})
public class LoanController {
    private LoanService loanService;


    public LoanController(LoanService loanService) {
        super();
        this.loanService = loanService;
    }

    @RequestMapping(value = {"/list", "/", "/search"})
    public String home(@RequestParam(value = "theSearchName", required = false) String theSearchName,
                       @PageableDefault(size = 5) Pageable pageable,
                       Model model) {
        if (theSearchName != null && !theSearchName.isEmpty()) {
            Page<Loan> list = loanService.getByKeyword(theSearchName, pageable);
            model.addAttribute("loans", list.getContent());
            model.addAttribute("totalPage", list.getTotalPages());
        } else {
            Page<Loan> list = loanService.getAllLoan(pageable);
            model.addAttribute("loans", list.getContent());
            model.addAttribute("totalPage", list.getTotalPages());
        }
        model.addAttribute("theSearchName", theSearchName != null ? theSearchName : "");
        model.addAttribute("page", pageable.getPageNumber());
        model.addAttribute("sortBy", pageable.getSort().get().count() != 0 ?
                pageable.getSort().get().findFirst().get().getProperty() + "," + pageable.getSort().get().findFirst().get().getDirection() : "");
        return "list-loan";
    }

    @GetMapping("/showFormForAdd")
    public String createLoanForm(Model model) {
        Loan loan = new Loan();
        model.addAttribute("loan", loan);
        return "loan-add";

    }

    @PostMapping("/saveLoan")
    public String saveLoan(@Valid @ModelAttribute("loan") Loan loan, BindingResult bindingResult, Model theModel) {
        if (bindingResult.hasErrors()) {
            return "loan-add";
        }
        loanService.saveLoan(loan);
        return "redirect:/loan/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("loanId") Long loanId, Model theModel) {
        theModel.addAttribute("loan", loanService.getLoanById(loanId));
        return "loan-add";
    }

    @GetMapping("/showFormForDelete")
    public String showFormForDelete(@RequestParam("loanId") Long loanId, Model theModel) {
        loanService.deleteLoanById(loanId);
        return "redirect:/loan/list";

    }

    @GetMapping("/updateStatus")
    public String updateStatus(@RequestParam("active") boolean active, @RequestParam("id") Long id, Model theModel) {
        loanService.updateLoanStatus(active, id);
        return "redirect:/loan/list";

    }
}
