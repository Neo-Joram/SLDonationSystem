package com.yoramu.controller;

import com.yoramu.model.Account;
import com.yoramu.service.AccService;
import com.yoramu.service.AccountService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Enumeration;

@Controller
public class AccountCont {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AccountService accountService;
    @Resource(name = "authenticationProvider")
    DaoAuthenticationProvider authenticationProvider;

    @GetMapping("/login")
    String login(Model model){
        model.addAttribute("accountModel", new Account());
        return "index";
    }

    @PostMapping("/createAccount")
    String createAccount(@ModelAttribute Account account) {
        account.setRole("Admin");
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        accountService.createAccount(account);

        return "redirect:login";
    }

    @PostMapping("/userLogin")
    public String userLogin(@ModelAttribute Account account, RedirectAttributes redirectAttributes, HttpSession session) {
        try {
            Authentication authentication = authenticationProvider.authenticate(
                    new UsernamePasswordAuthenticationToken(account.getEmail(), account.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            System.out.println("Authenticated: " + authentication);

            System.out.println("Session ID: " + session.getId());
            System.out.println("Creation Time: " + session.getCreationTime());
            System.out.println("Last Accessed Time: " + session.getLastAccessedTime());

            return "redirect:/admin";
        } catch (BadCredentialsException ex) {
            redirectAttributes.addFlashAttribute("error", "Invalid username or password");
            return "redirect:login";
        }
    }
}
