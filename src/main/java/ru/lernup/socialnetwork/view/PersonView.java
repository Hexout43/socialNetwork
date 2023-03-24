package ru.lernup.socialnetwork.view;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class PersonView {
    @JsonProperty
    private Long id;
    @JsonProperty
    private String name;
    @JsonProperty
    private String surname;
    @JsonProperty
    private String birthDate;
    @JsonProperty
    private String briefInformation;
    @JsonProperty
    private Long idUser;
    @JsonProperty
    private String mail;
    @JsonProperty
    private List<PersonView> friends;
}
