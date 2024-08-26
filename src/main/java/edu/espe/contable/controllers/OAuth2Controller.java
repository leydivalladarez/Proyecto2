package edu.espe.contable.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.context.SecurityContextHolder;

import jakarta.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
public class OAuth2Controller {

    @GetMapping("/oauth2/callback")
    public RedirectView oauth2Callback(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof OAuth2User) {
            OAuth2User principal = (OAuth2User) authentication.getPrincipal();
            model.addAttribute("name", principal.getAttribute("name"));
            return new RedirectView("http://localhost:3000/dashboard");
        } else {
            return new RedirectView("http://localhost:3000/login?error=true");
        }
    }
}