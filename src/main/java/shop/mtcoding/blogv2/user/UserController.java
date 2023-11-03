package shop.mtcoding.blogv2.user;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class UserController {

    @PostMapping("/user/update")
    public String update(@Valid UserRequest.PasswordUpdateDTO requestDTO, Errors errors) {
        return "redirect:/";
    }

    @PostMapping("/join")
    public String join(@Valid UserRequest.JoinDTO requestDTO, Errors errors){
        return "redirect:/loginForm";
    }

    @PostMapping("/login")
    public String login(@Valid UserRequest.LoginDTO requestDTO, Errors errors){
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/";
    }

    @GetMapping("/joinForm")
    public String joinForm() {
        return "user/joinForm";
    }

    @GetMapping("/loginForm")
    public String loginForm() {
        return "user/loginForm";
    }

    @GetMapping("/user/updateForm")
    public String updateForm() {
        return "user/updateForm";
    }
}
