package ru.lernup.socialnetwork.service;

import org.springframework.stereotype.Service;
import ru.lernup.socialnetwork.Db.Entity.Album;
import ru.lernup.socialnetwork.Db.Entity.FilePerson;
import ru.lernup.socialnetwork.Db.Repository.AlbumRepository;
import ru.lernup.socialnetwork.Db.Repository.PersonRepository;
import ru.lernup.socialnetwork.mapper.AlbumMapper;
import ru.lernup.socialnetwork.view.AlbumView;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlbumService {
    private final AlbumMapper albumMapper;
    private final AlbumRepository albumRepository;
    private final PersonRepository personRepository;
    private final GenerateContent generateContent;

    public AlbumService(AlbumMapper albumMapper,
                        AlbumRepository albumRepository,
                        PersonRepository personRepository,
                        GenerateContent generateContent) {
        this.albumMapper = albumMapper;
        this.albumRepository = albumRepository;
        this.personRepository = personRepository;
        this.generateContent = generateContent;
    }
    public List<AlbumView> getAlbumByPerson(Long idAuthor){
        return
        albumRepository.getAlbumsByPerson(personRepository.getReferenceById(idAuthor)).stream()
                .map(albumMapper::mappedToView).collect(Collectors.toList());

    }
    public AlbumView getAlbumById(Long id){

        return albumMapper.mappedToView(albumRepository.getReferenceById(id));
    }
    public  void addAlbum(AlbumView albumView){

        albumRepository.save(albumMapper.mappedFromView(albumView));
    }
    public void addContentInAlbum(AlbumView albumView){
        Album album = albumRepository.getReferenceById(albumView.getId());
        albumView.getContent().stream().forEach(content->{
            FilePerson filePerson =generateContent.generateContent(content);
            filePerson.setAlbum(album);
            album.getFilePeople().add(filePerson);
        });
        albumRepository.save(album);

    }

}
