package com.alpersayin.aop;

import com.alpersayin.entity.BookEntity;
import com.alpersayin.entity.OrderEntity;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    public Optional<String> getCurrentUser() {
//        return Optional.ofNullable(SecurityContextHolder.getContext())
//                .map(SecurityContext::getAuthentication)
//                .filter(Authentication::isAuthenticated)
//                .map(Authentication::getPrincipal)
//                .map(User.class::cast)
//                .map(User::getUsername);
        return Optional.of("alper_auditor");
    }

    @Pointcut("execution(* com.alpersayin.service.BookService.create*(..))")
    protected void bookServiceMethods() {}

    @AfterReturning(pointcut = "bookServiceMethods()", returning = "result")
    private void afterReturningBookCreateMethods(Object result) {
        BookEntity book = (BookEntity) result;
        log.info("New Book added with id: {} by User with username: {}", book.getId(), getCurrentUser().get());
    }

    @Pointcut("execution(* com.alpersayin.service.BookServiceImpl.stock*(..))")
    protected void bookServiceStockMethods() {}

    @AfterReturning(pointcut = "bookServiceStockMethods()", returning = "result")
    private void afterReturningStockMethods(Object result) {
        BookEntity book = (BookEntity) result;
        log.info("Book stock changed with id: {} by User with username: {}", book.getId(), getCurrentUser().get());
    }

    @Pointcut("execution(* com.alpersayin.service.OrderService.create*(..))")
    protected void orderServiceCreateMethods() {}

    @AfterReturning(pointcut = "orderServiceCreateMethods()", returning = "result")
    private void afterReturningOrderCreateMethods(Object result) {
        OrderEntity order = (OrderEntity) result;
        log.info("New Order CREATED with id: {} by User with username: {}", order.getId(), getCurrentUser().get());
    }

}
