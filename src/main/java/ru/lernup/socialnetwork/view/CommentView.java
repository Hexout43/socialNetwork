package ru.lernup.socialnetwork.view;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CommentView {
    @JsonProperty
    private Long id;
    @JsonProperty
    private String text;
    @JsonProperty
    private Long idAuthor;
    @JsonProperty
    private Long idPost;
}
