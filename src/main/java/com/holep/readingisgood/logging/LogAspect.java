package com.holep.readingisgood.logging;

import com.google.gson.Gson;
import com.holep.readingisgood.auth.data.AuthUser;
import com.holep.readingisgood.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Aspect
@Slf4j
@Component
@RequiredArgsConstructor
public class LogAspect {

    final LogService logService;

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void restControllerPointcut() {

    }

    @AfterThrowing(pointcut = "restControllerPointcut()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Exception e) throws IOException {

        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                        .getRequest();

        ErrorLog.ErrorLogBuilder errorLogBuilder = ErrorLog.builder()
                .endpoint(request.getRequestURL().toString())
                .method(request.getMethod())
                .ipAddress(request.getRemoteAddr())
                .message(e.getMessage())
                .errorClass(e.getClass().getName())
                .javaMethod(joinPoint.getSignature().getName());


        if (e instanceof BusinessException) {
            errorLogBuilder.status(((BusinessException) e).getCode());
        } else {
            errorLogBuilder.status("500");
        }

        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            errorLogBuilder
                    .authenticatedUser(((AuthUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
                            .getId());
        }

        logService.error(errorLogBuilder.build());
    }

    @AfterReturning(value = "restControllerPointcut()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) throws IOException {

        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                        .getRequest();

        InfoLog.InfoLogBuilder logBuilder = InfoLog.builder()
                .endpoint(request.getRequestURL().toString())
                .method(request.getMethod())
                .ipAddress(request.getRemoteAddr())
                .responseBody(new Gson().toJson(result));

        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            logBuilder
                    .authenticatedUser(((AuthUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
                            .getId());
        }

        logService.info(logBuilder.build());
    }
}
