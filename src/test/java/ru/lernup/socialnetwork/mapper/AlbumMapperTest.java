package ru.lernup.socialnetwork.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.lernup.socialnetwork.Db.Entity.Album;
import ru.lernup.socialnetwork.Db.Entity.FilePerson;
import ru.lernup.socialnetwork.Db.Entity.Person;
import ru.lernup.socialnetwork.Db.Repository.PersonRepository;
import ru.lernup.socialnetwork.service.GenerateContent;
import ru.lernup.socialnetwork.view.AlbumView;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class AlbumMapperTest {
    Person person;
    String urlImg = "http://localhost:8189/file/img?name=";
    @Mock
    PersonRepository personRepository;
    @Mock
    GenerateContent generateContent;
    @InjectMocks
    AlbumMapper albumMapper;
    @Test
    void mappedFromView(){
        //given
        generatePerson();
        AlbumView albumView = new AlbumView();
        albumView.setIdAuthor(1L);
        albumView.setId(1L);
        FilePerson filePerson = new FilePerson();
        filePerson.setName("12");
        filePerson.setId(1L);
        albumView.setContent(List.of(urlImg+"12"));
        Mockito.doReturn(filePerson).when(this.generateContent).generateContent(urlImg + "12");
        Mockito.doReturn(person).when(this.personRepository).getReferenceById(1L);
        //when
        var album = albumMapper.mappedFromView(albumView);
        //then
        assertEquals(album.getId(),albumView.getId());
        assertEquals(album.getPerson().getId(),albumView.getIdAuthor());
        assertArrayEquals(album.getFilePeople().toArray(),albumView.getContent()
                .stream().map(generateContent::generateContent).toArray());
    }
    void generatePerson(){
        person = new Person();
        person.setId(1L);
        person.setName("Steve");
    }
    @Test
    void mappedToView(){
        //given
        generatePerson();
        Album album = new Album();
        album.setPerson(person);
        album.setId(1L);
        FilePerson filePerson = new FilePerson();
        filePerson.setId(1L);
        filePerson.setName("12");
        album.setFilePeople(List.of(filePerson));
        //when
        var albumView = albumMapper.mappedToView(album);
        //then
        assertEquals(albumView.getId(),album.getId());
        assertEquals(albumView.getIdAuthor(),album.getPerson().getId());
        assertArrayEquals(albumView.getContent().toArray(),album.getFilePeople().stream()
                .map(generateContent::generateUrl).toArray());
    }

}