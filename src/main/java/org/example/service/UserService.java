package org.example.service;

import org.example.dto.UserDTO;
import org.example.entity.Role;
import org.example.entity.User;
import org.example.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public String addUser(UserDTO dto) {
        if (!dto.getUserName().isEmpty() &&!dto.getUserEmail().isBlank() &&
                dto.getPassword().length() > 5) {
            User user = new User();
            user.setUserName(dto.getUserName());
            user.setUserPassword(dto.getPassword());
            user.setUserEmail(dto.getUserEmail());
            userRepository.save(user);
        }
        return "successful";
    }

    public String register(UserDTO dto, Model model) {//注册条件等
        if (!dto.getUserName().isEmpty() &&!dto.getUserEmail().isBlank() &&
                dto.getPassword().length() > 5) {
            User user = new User();
            user.setUserName(dto.getUserName());
            user.setUserEmail(dto.getUserEmail());
            user.setUserPassword(dto.getPassword());
            userRepository.save(user);
            return "success";
        }
        return "Register";
    }

    public String Login(String email, String password ,Model model) {
        User user = userRepository.findByUserEmailAndUserPassword(email, password);
        if ( user!= null && user.getUserEmail().equals(email) && user.getUserPassword().equals(password)) {
            return "index";
        }
        else{
            model.addAttribute("error","Login error");
            return "Login";
        }
    }
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User getByUsername(String username) {
        return userRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
    }


    public User create(User user) {
        if(userRepository.existsByUserName(user.getUsername())){
            throw new RuntimeException("Пользователь с таким именем уже существует");
        }
        if(userRepository.existsByUserEmail(user.getUserEmail())){
            throw  new RuntimeException("Пользователь с таким email уже существует");
        }
        return save(user);
    }

    public UserDetailsService userDetailsService() {
        return this::getByUsername;
    }

    public User getCurrentUser() {
        // Получение имени пользователя из контекста Spring Security
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByUsername(username);
    }

    @Deprecated
    public void getAdmin() {
        var user = getCurrentUser();
        user.setRole(Role.ROLE_ADMIN);
        save(user);
    }

    private User save(User user) {
        return userRepository.save(user);
    }
}