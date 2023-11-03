package shop.mtcoding.blogv2.temp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/temp")
@Controller
public class TempController {

    @GetMapping({"", "/", "/board"})
    public String home(){
        return "index";
    }

    @GetMapping("/loginForm")
    public String loginForm(){
        return "user/loginForm";
    }

    @GetMapping("/joinForm")
    public String joinForm(){
        return "user/joinForm";
    }

    @GetMapping("/user/updateForm") // board에도 updateForm이 있기 때문에
    public String userUpdateForm(){
        return "user/updateForm";
    }

    @GetMapping("/board/{id}")
    public String boardDetail(@PathVariable Integer id){
        return "board/detail";
    }

    @GetMapping("/board/saveForm")
    public String saveForm(){
        return "board/saveForm";
    }

    @GetMapping("/board/updateForm")
    public String boardUpdateForm(){
        return "board/updateForm";
    }

}
