package com.jwis.demo.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@Aspect
@Component
public class AopConfig {
//    private Logger logger = LoggerFactory.getLogger(this.getClass());
//
//    @Pointcut("execution(public * com.jwis.demo.ctrl.*.*Part(..))&&execution(public * com.jwis.demo.ctrl.*.*Data(..))&&execution(public * com.jwis.demo.ctrl.*.*Bop(..))&&execution(public * com.jwis.demo.ctrl.*.*List(..))")
//    public void log() {
//    }
//
//    @Before("log()")
//    public void doBefore(JoinPoint joinPoint) {
//        logger.info("aop doBefore..");
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
//
//        //url
//        logger.info("url={}",request.getRequestURI());
//
//        //method
//        logger.info("method={}", request.getMethod());
//
//        //ip
//        logger.info("ip={}", request.getRemoteAddr());
//
//        //类方法
//        logger.info("classMethod={}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
//
//        //参数
//        Enumeration<String> paramter = request.getParameterNames();
//        while (paramter.hasMoreElements()) {
//            String str = (String) paramter.nextElement();
//            logger.info(str + "={}", request.getParameter(str));
//        }
//
//    }
//
//    @After("log()")
//    public void doAfter() {
//        logger.info("aop doAfter");
//    }
}
