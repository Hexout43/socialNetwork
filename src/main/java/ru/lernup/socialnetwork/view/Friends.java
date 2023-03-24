package ru.lernup.socialnetwork.view;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Setter
@Getter
public class Friends {
    private Long idPerson;
    private List<PersonView> friends;
}
