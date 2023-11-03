package shop.mtcoding.blogv2._core.error;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import shop.mtcoding.blogv2._core.error.ex.Exception400;
import shop.mtcoding.blogv2._core.util.Script;

@ControllerAdvice
public class MyExceptionHandler {

    @ResponseBody
    @ExceptionHandler(Exception400.class)
    public String ex400(Exception400 e){
        return Script.back(e.getMessage());
    }
}
