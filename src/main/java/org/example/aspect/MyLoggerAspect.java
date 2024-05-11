package org.example.aspect;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.example.entity.MyLogger;
import org.example.service.MyLoggerService;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.stream.Collectors;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class MyLoggerAspect {

    private final MyLoggerService loggerService;

    @Around(value = "@annotation(org.example.aspect.ToLogOurApp)")
    public Object myLogMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Aspect is working");

        String answer = (String) joinPoint.proceed();
        String request =
                Arrays.stream(joinPoint.getArgs())
                        .map(Object::toString)
                        .collect(Collectors.joining(","));



        MyLogger newLog =  new org.example.entity.MyLogger(null,
                request,
                answer, LocalDateTime.now()
                );

        loggerService.log(newLog);

        log.info("in->{}, out->{}", request, answer);

        return answer;
    }
}
