package shop.mtcoding.blogv2.reply;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ReplyController {

    // 디비 쿼리에 where절에 들어가는 값중에 unique한 값은 주소로 받는다.
    // delete from reply_tb where id = :replyId
    @PostMapping("/board/{boardId}/reply/{replyId}/delete")
    public String delete(@PathVariable Integer boardId, @PathVariable Integer replyId){
        return "redirect:/board/"+boardId;
    }


    @PostMapping("/reply/save")
    public String save(@RequestBody ReplyRequest.SaveDTO requestDTO){
        System.out.println(requestDTO);
        return "redirect:/board/"+requestDTO.getBoardId();
    }
}
