package de.springboot.web;

import de.springboot.dto.LoginDTO;
import de.springboot.exceptions.UserNotFoundException;
import de.springboot.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping
    public String getLoginForm(@RequestParam(value = "error", required = false) String error,
                               @RequestParam(value = "logout", required = false) String logout,
                               Model model) {
        model.addAttribute("error", error != null);
        model.addAttribute("logout", logout != null);
        return "login";
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping
    public void loginUser(LoginDTO dto) throws UserNotFoundException {
        loginService.getUser(dto);
    }

}
