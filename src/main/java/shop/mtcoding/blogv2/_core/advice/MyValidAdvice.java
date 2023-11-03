package shop.mtcoding.blogv2._core.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import shop.mtcoding.blogv2._core.error.ex.Exception400;

@Aspect
@Component
public class MyValidAdvice {

    @Before("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void validationAdvice(JoinPoint jp) throws Throwable {
        Object[] args = jp.getArgs();
        for (Object arg : args) {
            if (arg instanceof Errors) {
                Errors errors = (Errors) arg;

                if (errors.hasErrors()) {
                    for (FieldError error : errors.getFieldErrors()) {
                        throw new Exception400(error.getDefaultMessage()+" : "+error.getField());
                    }
                }
            }
        }
    }
}