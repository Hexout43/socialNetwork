package ru.lernup.socialnetwork.view;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Data
public class UserRegister {
    @JsonProperty
    private String login;
    @JsonProperty
    private String password;
    @JsonProperty(defaultValue = "User")
    private List<String> roles;

}
