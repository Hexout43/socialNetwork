package ru.lernup.socialnetwork.mapper;

import org.springframework.stereotype.Component;
import ru.lernup.socialnetwork.Db.Entity.Album;
import ru.lernup.socialnetwork.Db.Entity.FilePerson;
import ru.lernup.socialnetwork.Db.Repository.PersonRepository;
import ru.lernup.socialnetwork.service.GenerateContent;
import ru.lernup.socialnetwork.view.AlbumView;

import java.util.stream.Collectors;

@Component
public class AlbumMapper {

    private final PersonRepository personRepository;
    private final GenerateContent generateContent;

    public AlbumMapper(PersonRepository personRepository,
                       GenerateContent generateContent) {
        this.personRepository = personRepository;
        this.generateContent = generateContent;
    }

    public Album mappedFromView(AlbumView albumView){

        Album album = new Album();
        album.setId(albumView.getId());
        album.setPerson(personRepository.getReferenceById(albumView.getIdAuthor()));
        album.setFilePeople(albumView.getContent().stream().map(content->{
            FilePerson filePerson = generateContent.generateContent(content);
            filePerson.setAlbum(album);
            return filePerson;
        }).collect(Collectors.toList()));
        return album;
    }
    public AlbumView mappedToView(Album album){
        AlbumView albumView = new AlbumView();
        albumView.setId(album.getId());
        albumView.setIdAuthor(album.getPerson().getId());
        albumView.setContent(album.getFilePeople().stream().map(generateContent::generateUrl).collect(Collectors.toList()));
        return albumView;
    }
}
