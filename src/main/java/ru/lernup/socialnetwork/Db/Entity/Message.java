package ru.lernup.socialnetwork.Db.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String text;
    @Column
    private String date;
    @ManyToOne
    @JoinColumn(name = "id_author")
    private Person idAuthor;
    @ManyToOne
    @JoinColumn(name = "id_recipient")
    private Person idRecipient;
}
