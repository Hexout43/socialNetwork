package ru.lernup.socialnetwork.Db.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "header")
    private String header;
    @Column(name = "body")
    private String body;
    @ManyToOne
    @JoinColumn(name = "id_author")
    private Person person;
    @OneToMany(mappedBy = "idPost",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<Comment> comments;
    @OneToMany(mappedBy = "post",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<FilePerson> content;
}
