package shop.mtcoding.blogv2.board;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
public class BoardController {

    @PostMapping("/board/save")
    public String save(@Valid BoardRequest.SaveDTO requestDTO, Errors errors) {
        System.out.println(requestDTO);
        return "redirect:/";
    }

    @PostMapping("/board/{id}/delete")
    public @ResponseBody String delete(@PathVariable Integer id) {
        return "redirect:/";
    }

    @PostMapping("/board/{id}/update")
    public String update(@PathVariable Integer id, @Valid BoardRequest.UpdateDTO requestDTO, Errors errors) {
        System.out.println(requestDTO);
        return "redirect:/board/" + id;
    }

    @GetMapping("/board/{id}/updateForm")
    public String updateForm(@PathVariable Integer id) {
        return "board/updateForm";
    }

    @GetMapping("/board/{id}")
    public String detail(@PathVariable Integer id) {
        return "board/detail";
    }

    @GetMapping({"", "/", "/board"})
    public String index() {
        return "index";
    }

    @GetMapping("/board/saveForm")
    public String saveForm() {
        return "board/saveForm";
    }
}
