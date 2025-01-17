package com.github.pieter_groenendijk;


import com.github.pieter_groenendijk.controller.LoanController;
import com.github.pieter_groenendijk.model.DTO.LoanRequestDTO;
import com.github.pieter_groenendijk.repository.ILoanRepository;
import com.github.pieter_groenendijk.service.loan.ILoanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;


public class LoanWebController {

    private final LoanController loanController;
    private final ILoanService loanService;
    private final ILoanRepository loanRepository;

    private final RestTemplate restTemplate;


    public LoanWebController(LoanController loanController, ILoanService loanService, ILoanRepository loanRepository, RestTemplate restTemplate) {
        this.loanController = loanController;
        this.loanService = loanService;
        this.loanRepository = loanRepository;
        this.restTemplate = restTemplate;
    }


    @GetMapping("/loan")
    public String showLoanForm(Model model) {
        LoanRequestDTO loanRequestDTO = new LoanRequestDTO();
        loanRequestDTO.setStartDate(LocalDate.now()); //

        model.addAttribute("loan", loanRequestDTO);
        model.addAttribute("today", LocalDate.now());

        return "loan";
    }

    @PostMapping("/loan")
    public String processLoanForm(@ModelAttribute LoanRequestDTO loanRequestDTO) {
        System.out.println("Loan submitted: " + loanRequestDTO);

        try {
            String url = "http://localhost:8080/loan";
            ResponseEntity<Void> response = restTemplate.postForEntity(url, loanRequestDTO, Void.class);

            if (response.getStatusCode() == HttpStatus.CREATED) {
                return "redirect:/loan/success";
            } else {
                return "redirect:/loan/failure";
            }
        } catch (Exception e) {
            return "redirect:/loan/failure";
        }
    }

}

