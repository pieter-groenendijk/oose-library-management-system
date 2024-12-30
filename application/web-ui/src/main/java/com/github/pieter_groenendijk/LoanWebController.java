package com.github.pieter_groenendijk;

import com.github.pieter_groenendijk.model.Loan;
import com.github.pieter_groenendijk.repository.ILoanRepository;
import com.github.pieter_groenendijk.service.ILoanService;
import com.github.pieter_groenendijk.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoanWebController {

    private final ILoanService loanService;
    private final ILoanRepository loanRepository;

    @Autowired
    public LoanWebController(ILoanService loanService, ILoanRepository loanRepository) {
        this.loanService = loanService;
        this.loanRepository = loanRepository;
    }
    

    @GetMapping("/loan/new")
    public String showLoanForm(Model model) {
        Loan loan = new Loan();
        model.addAttribute("loan", new Loan());
        return "loan/new-loan";
    }

    @PostMapping("/loan/new")
    public String processLoanForm(@ModelAttribute Loan loan) {
        System.out.println("Loan submitted: " + loan);
        return "redirect:/loan/success";
    }
}
