package com.spring.SimpleLogin.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.SimpleLogin.account.*;
import com.spring.SimpleLogin.signup.SignupViewModel;
import com.spring.SimpleLogin.support.web.*;

@Controller
public class SignupController {

    private static final String SIGNUP_VIEW_NAME = "signup/signup";

	@Autowired
	private AccountRepository accountRepository;
	
	@RequestMapping(value = "signup")
	public String signup(Model model) 
	{
		model.addAttribute(new SignupViewModel());
        return SIGNUP_VIEW_NAME;
	}
	
	@RequestMapping(value = "signup", method = RequestMethod.POST)
	public String signup(@Valid @ModelAttribute SignupViewModel signupForm, Errors errors, RedirectAttributes ra) 
	{
		if (errors.hasErrors()) 
		{
			return SIGNUP_VIEW_NAME;
		}
		
		Account account = accountRepository.save(signupForm.createAccount());
        MessageHelper.addSuccessAttribute(ra, "signup.success");
        
		return "redirect:/";
	}
}
