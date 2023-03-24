package ru.lernup.socialnetwork.view;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.boot.context.properties.bind.DefaultValue;
import ru.lernup.socialnetwork.Db.Entity.Comment;
import ru.lernup.socialnetwork.Db.Entity.Person;

import java.util.List;
@Data
public class PostView {
    @JsonProperty
    private Long id;
    @JsonProperty
    private String header;
    @JsonProperty
    private String body;
    @JsonProperty
    private Long person;
    @JsonProperty
    private List<String> comments;
    @JsonProperty
    private List<String> content;
}
