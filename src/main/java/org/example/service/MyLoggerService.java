package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.entity.MyLogger;
import org.example.repository.LoggerRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class MyLoggerService {
    private final LoggerRepository repository;

    public MyLogger log(MyLogger log) {
        return repository.save(log);
    }

}
