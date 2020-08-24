package com.practice.annotation;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PermissionAspect {

    @Before("@annotation(hasUserPermission)")
    public void evaluate(JoinPoint joinPoint,
        HasUserPermission hasUserPermission) throws Throwable {

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        Parameter[] parameters = method.getParameters();

        System.out.println("permissions - " + hasUserPermission.permissions()[0]);
        System.out
            .println("parameter - " + parameters[0].getName() + ", " + joinPoint.getArgs()[0]);

        if (Arrays.stream(hasUserPermission.permissions()).noneMatch(p -> p.equalsIgnoreCase(
            "view_employee"))) {
            throw new Exception("No permission to view employee");
        }

    }
}
