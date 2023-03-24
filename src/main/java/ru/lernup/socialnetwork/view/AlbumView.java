package ru.lernup.socialnetwork.view;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Getter

public class AlbumView {
    @JsonProperty
    private Long id;
    @JsonProperty
    private Long idAuthor;
    @JsonProperty
    private List<String> content;
}
