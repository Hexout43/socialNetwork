package ru.lernup.socialnetwork.Db.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Entity
@Table(name = "album")
@Setter
@Getter
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;
    @OneToMany(mappedBy = "album",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<FilePerson> filePeople;
}
