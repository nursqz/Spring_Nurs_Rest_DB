package org.example.service;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
@Service
@SessionScope
public class LogeedUserService {
    private String userEmail;
    public String getUserEmail() {
        return userEmail;
    }
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
