package shop.mtcoding.blogv2.board;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

public class BoardRequest {

    @Data
    public static class UpdateDTO {
        @NotEmpty
        private String title;
        @NotEmpty
        private String content;
    }

    @Data
    public static class SaveDTO {
        @NotEmpty
        private String title;
        @NotEmpty
        private String content;
    }
}
