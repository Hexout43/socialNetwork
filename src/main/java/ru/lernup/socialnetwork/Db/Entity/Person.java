package ru.lernup.socialnetwork.Db.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String surname;
    @Column(name = "birth_date")
    private String birthdate;
    @Column(name = "brief_information")
    private String briefInformation;
    @Column(name = "mail")
    private String mail;
    @Column(name = "friends")
    private String friends;
    @OneToMany(mappedBy = "person",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<Post> posts;
    @OneToMany(mappedBy = "idAuthor",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Comment> comments;
    @OneToMany(mappedBy = "idAuthor",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<Message> authorMessage;
    @OneToMany(mappedBy = "idRecipient",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<Message> recipientMessage;
    @OneToOne
    @JoinColumn(name = "id_user")
    public User user;
    @OneToMany(mappedBy = "person",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    public List<FilePerson> filePeople;
    @OneToMany(mappedBy = "person",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    public List<Album> albums;

}
