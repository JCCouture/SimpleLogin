package com.spring.SimpleLogin.controllers;

import java.security.Principal;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.spring.SimpleLogin.account.Account;
import com.spring.SimpleLogin.account.AccountRepository;

@Controller
class AccountController {

    private AccountRepository accountRepository;

    @Autowired
    public AccountController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @RequestMapping(value = "account/current", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public Account accounts(Principal principal) {
        Assert.notNull(principal);
        return accountRepository.findByEmail(principal.getName());
    }
    
    @RequestMapping(value = "/listAllAccount", method = RequestMethod.GET)
	public ModelAndView listAllAccount()
	{	
    	ModelAndView mav = new ModelAndView("account/listAllAccount");
    	    	
    	mav.addObject("accounts",  accountRepository.findAll());
    	
		return mav;
	}
}
