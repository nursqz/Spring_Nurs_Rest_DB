package org.example.controller;

import org.example.dto.UserDTO;
import org.example.service.UserService;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegisterController {

    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/register")
    public String showRegister(Model model){
        model.addAttribute("user",new UserDTO());
        return "register";
    }
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String Register( UserDTO userDTO,Model model) {
        String result=userService.register(userDTO, model);

        if ("success".equals(result)) {
            return "redirect:/login";
        } else {
            model.addAttribute("error", "Пароль должен быть не менее 6 символов");
            return "register";
        }
    }

}

