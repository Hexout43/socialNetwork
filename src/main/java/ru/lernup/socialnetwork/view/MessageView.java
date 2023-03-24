package ru.lernup.socialnetwork.view;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data

public class MessageView {
    @JsonProperty
    private String text;
    @JsonProperty
    private String date;
    @JsonProperty
    private Long idAuthor;
    @JsonProperty
    private String content;
    @JsonProperty
    private Long idRecipient;
}
