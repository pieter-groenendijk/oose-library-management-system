package com.github.pieter_groenendijk;
/* //Dit is omdat de andere branche nog WIP is, dus LoanRequestDTO bestaat nog niet volgens deze branche

import com.github.pieter_groenendijk.model.DTO.LoanRequestDTO;
import com.github.pieter_groenendijk.repository.ILoanRepository;
import com.github.pieter_groenendijk.service.loan.ILoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;

@Controller
public class LoanWebController {

    private final ILoanService loanservice;
    private final ILoanRepository loanRepository;



    @Autowired
    public LoanWebController(ILoanService loanService, ILoanService loanservice, ILoanRepository loanRepository) {
        this.loanservice = loanservice;
        this.loanRepository = loanRepository;
    }
    

    @GetMapping("/loan")
    public String showLoanForm(Model model) {
        LoanRequestDTO loanRequestDTO = new LoanRequestDTO();
        model.addAttribute("loanRequestDTO", loanRequestDTO);
        model.addAttribute("today", LocalDate.now()); //
        return "/loan";
    }


    @PostMapping("/loan")
    public String processLoanForm(@ModelAttribute LoanRequestDTO loanRequestDTO) {
        System.out.println("Loan submitted: " + loan);
        return "redirect:/loan/success";
    }
}
*/